/*
 * Copyright (C) 2015 by IMDEA Networks Institute
 *
 * This file is part of Electrosense.
 *
 * Electrosense is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Electrosense is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with RTL-Spec.  If not, see <http://www.gnu.org/licenses/>.
 *
 * 	Authors:
 * 	    Roberto Calvo Palomino <roberto.calvo@imdea.org>
 *
 */

#include <iostream>
#include <iostream>
#include <string>
#include <vector>
#include <unistd.h>

#include <boost/any.hpp>

#include "generated/version_config.h"
#include "context/ElectrosenseContext.h"

#include "drivers/rtlsdr/rtlsdrDriver.h"

#include "types/SpectrumSegment.h"
#include "ProcessingBlocks/RemoveDC.h"
#include "ProcessingBlocks/Windowing.h"
#include "drivers/Component.h"
#include "types/SpectrumSegment.h"
#include "ProcessingBlocks/FFT.h"
#include "ProcessingBlocks/Averaging.h"
#include "ProcessingBlocks/FileSink.h"


void usage (char* name)
{

    fprintf(stderr,
            "Usage:\n"
                    "  %s min_freq max_freq\n"
                    "  [-h]\n"
                    "  [-d <dev_index>]\n"
                    "  [-c <clk_off>] [-k <clk_corr_period>]\n"
                    "  [-g <gain>]\n"
                    "  [-y <hopping_strategy>]\n"
                    "  [-s <samp_rate>]\n"
                    "  [-f <log2_fft_size>] [-b <fft_batchlen>]\n"
                    "  [-a <avg_factor>] [-o <soverlap>] [-q <freq_overlap>]\n"
                    "  [-t <monitor_time>] [-r <min_time_res>]\n"
                    "  [-w <window>]\n"
                    "  [-l <cmpr_level>]\n"
                    "  [-m <hostname1>:<portnumber1>[;<bandwidth1>],...,<hostnameN>:<portnumberN>[;bandwidthN]]\n"
                    "\n"
                    "Arguments:\n"
                    "  min_freq               Lower frequency bound in Hz\n"
                    "  max_freq               Upper frequency bound in Hz\n"
                    "\n"
                    "Options:\n"
                    "  -h                     Show this help\n"
                    "  -d <dev_index>         RTL-SDR device index [default=%i]\n"
                    "  -c <clk_off>           Clock offset in PPM [default=%i]\n"
                    "  -k <clk_corr_period>   Clock correction period in seconds [default=%u]\n"
                    "                           i.e. perform frequency correction every 'clk_corr_period'\n"
                    "                           seconds\n"
                    "  -g <gain>              Gain value in dB [default=%.1f]\n"
                    "                           -1 for automatic gain\n"
                    "  -y <hopping_strategy>  Hopping strategy to use [default=%s]\n"
                    "                           sequential\n"
                    "                           random\n"
                    "                           similarity\n"
                    "  -s <samp_rate>         Sampling rate in Hz [default=%u]\n"
                    "  -f <log2_fft_size>     Use FFT size of 2^'log2_fft_size' [default=%u]\n"
                    "                           the resulting frequency resolution is\n"
                    "                           'samp_rate'/(2^'log2_fft_size')\n"
                    "  -b <fft_batchlen>      FFT batch length [default=%u]\n"
                    "                           i.e. process FFTs in batches of length 'fft_batchlen'\n"
                    "  -a <avg_factor>        Averaging factor [default=%u]\n"
                    "                           i.e. average 'avg_factor' segments\n"
                    "  -o <soverlap>          Segment overlap [default=%u]\n"
                    "                           i.e. number of samples per segment that overlap\n"
                    "                           The time to dwell in seconds at a given frequency is given by\n"
                    "                           (((1<<'log2_fft_size')-'soverlap')*'avg_factor'+'soverlap')/'samp_rate'\n"
                    "  -q <freq_overlap>      Frequency overlapping factor [default=%.3f]\n"
                    "                           i.e. the frequency width is reduced from 'samp_rate' to\n"
                    "                           (1-'freq_overlap')*'samp_rate'\n"
                    "  -t <monitor_time>      Time in seconds to monitor [default=%u]\n"
                    "                           0 to monitor infinitely\n"
                    "  -r <min_time_res>      Minimal time resolution in seconds [default=%u]\n"
                    "                           0 for no time resolution limitation\n"
                    "  -w <window>            Windowing function [default=%s]\n"
                    "                           rectangular\n"
                    "                           hanning\n"
                    "                           blackman_harris_4\n"
                    "  -l <cmpr_level>        Compression level [default=%u]\n"
                    "                           0 for no compression, fastest\n"
                    "                           9 for highest compression, slowest\n"
                    "  -m <hostname1>:<portnumber1>[;<bandwidth1>],...,<hostnameN>:<portnumberN>[;<bandwidthN>]\n"
                    "                         TCP collector hosts [default=%s]\n"
                    "                           Bandwidth limitation in Kb/s\n"
                    " -p <true|false>         Set FIFO priority to the sampling thread [default=%s]\n"
                    "                           true\n"
                    "                           false\n"
                    "",
            name,
            ElectrosenseContext::getInstance()->getDevIndex(),
            ElectrosenseContext::getInstance()->getClkOffset(),
            ElectrosenseContext::getInstance()->getClkCorrPerior(),
            ElectrosenseContext::getInstance()->getGain(),
            ElectrosenseContext::getInstance()->getHoppingStrategy().c_str(),
            ElectrosenseContext::getInstance()->getSamplingRate(),
            ElectrosenseContext::getInstance()->getLog2FftSize(),
            ElectrosenseContext::getInstance()->getFFTbatchlen(),
            ElectrosenseContext::getInstance()->getAvgFactor(),
            ElectrosenseContext::getInstance()->getSoverlap(),
            ElectrosenseContext::getInstance()->getFreqOverlap(),
            ElectrosenseContext::getInstance()->getMonitorTime(),
            ElectrosenseContext::getInstance()->getMinTimeRes(),
            ElectrosenseContext::getInstance()->getWindowing().c_str(),
            ElectrosenseContext::getInstance()->getComprLevel(),
            ElectrosenseContext::getInstance()->getTcpHosts().c_str(),
            ElectrosenseContext::getInstance()->isFifoPriority() ? "true" : "false");


    exit(-1);
}

void parse_args(int argc, char *argv[])
{



    int opt;
    const char *options = "hd:c:k:g:y:s:f:b:a:o:q:t:r:w:l:m:p";

    // Option arguments
    while((opt = getopt(argc, argv, options)) != -1) {

        std::string argstr(optarg);

        switch(opt) {
            case 'h':
                usage(argv[0]);
                break;
            case 'd':
                ElectrosenseContext::getInstance()->setDevIndex(atoi(optarg));
                break;
            case 'c':
                ElectrosenseContext::getInstance()->setClkOff(atoi(optarg));
                break;
            case 'k':
                ElectrosenseContext::getInstance()->setClkCorrPerior(atoi(optarg));
                break;
            case 'g':
                ElectrosenseContext::getInstance()->setGain(atof(optarg));
                break;
            case 'y':
                ElectrosenseContext::getInstance()->setHoppingStrategy(argstr);
                break;
            case 's':
                ElectrosenseContext::getInstance()->setSamplingRate(atol(optarg));
                break;
            case 'f':
                ElectrosenseContext::getInstance()->setLog2FftSize(atol(optarg));
                break;
            case 'b':
                ElectrosenseContext::getInstance()->setFFTbatchlen(atol(optarg));
                break;
            case 'a':
                ElectrosenseContext::getInstance()->setAvgFactor(atol(optarg));
                break;
            case 'o':
                ElectrosenseContext::getInstance()->setSoverlap(atol(optarg));
                break;
            case 'q':
                ElectrosenseContext::getInstance()->setFreqOverlap(atof(optarg));
                break;
            case 't':
                ElectrosenseContext::getInstance()->setMonitorTime(atol(optarg));
                break;
            case 'r':
                ElectrosenseContext::getInstance()->setMinTimeRes(atol(optarg));
                break;
            case 'w':
                ElectrosenseContext::getInstance()->setWindowing(argstr);
                break;
            case 'l':
                ElectrosenseContext::getInstance()->setComprLevel(atol(optarg));
                break;
            case 'm':
                ElectrosenseContext::getInstance()->setTcpHosts(argstr);
                break;
            case 'p':
                ElectrosenseContext::getInstance()->setFifoPriority(1);
                break;

            default:
                usage(argv[0]);
        }
    }

    // Non-option arguments
    if(optind+2 != argc) {
        usage(argv[0]);
    } else {
        ElectrosenseContext::getInstance()->setMinFreq(atol(argv[optind]));
        ElectrosenseContext::getInstance()->setMaxFreq(atol(argv[optind+1]));
    }

}


int main( int argc, char* argv[] ) {


    std::cout << std::endl << "Electrosense sensing application (" << getElectrosenseVersion() << ")" << std::endl
              << std::endl;

    parse_args(argc, argv);
    ElectrosenseContext::getInstance()->print();

    // RTL-SDR Driver
    auto* rtlDriver = new electrosense::rtlsdrDriver();

    // RemoveDC Block
    auto* rdcBlock = new electrosense::RemoveDC();
    rdcBlock->setQueueIn( rtlDriver->getQueueOut() );

    // Windowing
    auto* winBlock = new electrosense::Windowing(electrosense::Windowing::HAMMING);
    winBlock->setQueueIn(rdcBlock->getQueueOut());

    // FFT
    auto* fftBlock = new electrosense::FFT();
    fftBlock->setQueueIn(winBlock->getQueueOut());

    // Averaging
    auto *avgBlock = new electrosense::Averaging();
    avgBlock->setQueueIn(fftBlock->getQueueOut());

    auto *fileSink = new electrosense::FileSink("/tmp/test.csv");
    fileSink->setQueueIn(avgBlock->getQueueOut());
    // Transmission

    rtlDriver->open("0");
    rtlDriver->start();
    rdcBlock->start();
    winBlock->start();
    fftBlock->start();
    avgBlock->start();
    fileSink->start();

    //for (unsigned int i=0; i<blocks.size(); i++)
    //    blocks[i]->start();



    while(1)
    {
        /*
        std::cout << rtlDriver->getNameId() << ": " << rtlDriver->getQueueOut()->size_approx();
        std::cout << " " << rdcBlock->getNameId() << " (OUT) : " << rdcBlock->getQueueOut()->size_approx();
        std::cout << " " << winBlock->getNameId() << " (OUT) : " << winBlock->getQueueOut()->size_approx();
        std::cout << " " << fftBlock->getNameId() << " (OUT) : " << fftBlock->getQueueOut()->size_approx();
        std::cout << " " << avgBlock->getNameId() << " (OUT) : " << avgBlock->getQueueOut()->size_approx();
        std::cout << " " << fileSink->getNameId() << "  (IN) : " << fileSink->getQueueIn()->size_approx() << std::endl;
         */
        sleep(1);
    }

    rtlDriver->stop();

    std::cout << "Ending properly!" << std::endl;
    return 0;
}