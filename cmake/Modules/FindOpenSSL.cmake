# Find the OpenSSL includes and libraries
#
# The following variables are set if OpenSSL is found. If it is not found, OpenSSL_FOUND is set to false.
#
#  OPENSSL_FOUND            - True when OpenSSL is found
#  OPENSSL_INCLUDE_DIRS     - The path to where the OpenSSL include files are located
#  OPENSSL_LIBRARIES        - The libraries to link to
#  OPENSSL_VERSION_MAJOR    - The major version of the library (e.g. 0 for 0.9.3)
#  OPENSSL_VERSION_MINOR    - The minor version of the library (e.g. 9 for 0.9.3)
#  OPENSSL_VERSION_PATCH    - The patch/subminor version of the library (e.g. 3 for 0.9.3)
#
# To specify an additional directory to search, set OPENSSL_ROOT.
#
# Copyright (c) 2006, Alexander Neundorf, <neundorf@kde.org>
# Modified and extended by Siddhartha Chaudhuri, 2008
#
# Redistribution and use is allowed according to the terms of the BSD license.
#

SET(OPENSSL_FOUND FALSE)

FIND_PATH(OPENSSL_INCLUDE_DIRS openssl/ssl.h PATHS ${OPENSSL_ROOT} ${OPENSSL_ROOT}/include NO_DEFAULT_PATH)
IF(NOT OPENSSL_INCLUDE_DIRS)
  FIND_PATH(OPENSSL_INCLUDE_DIRS openssl/ssl.h)
ENDIF(NOT OPENSSL_INCLUDE_DIRS)

IF(OPENSSL_INCLUDE_DIRS)
  IF(WIN32)

    FIND_LIBRARY(SSL_EAY_DEBUG NAMES ssleay32MDd PATH_SUFFIXES "" Debug PATHS ${OPENSSL_ROOT} ${OPENSSL_ROOT}/lib
                 NO_DEFAULT_PATH)
    IF(NOT SSL_EAY_DEBUG)  # now look in system locations
      FIND_LIBRARY(SSL_EAY_DEBUG NAMES ssleay32MDd PATH_SUFFIXES "" Debug)
    ENDIF(NOT SSL_EAY_DEBUG)

    FIND_LIBRARY(SSL_EAY_RELEASE NAMES ssleay32MD ssl ssleay32 PATH_SUFFIXES "" Release
                 PATHS ${OPENSSL_ROOT} ${OPENSSL_ROOT}/lib NO_DEFAULT_PATH)
    IF(NOT SSL_EAY_RELEASE)  # now look in system locations
      FIND_LIBRARY(SSL_EAY_RELEASE NAMES ssleay32MD ssl ssleay32 PATH_SUFFIXES "" Release)
    ENDIF(NOT SSL_EAY_RELEASE)

    FIND_LIBRARY(LIB_EAY_DEBUG NAMES libeay32MDd PATH_SUFFIXES "" Debug PATHS ${OPENSSL_ROOT} ${OPENSSL_ROOT}/lib
                 NO_DEFAULT_PATH)
    IF(NOT LIB_EAY_DEBUG)  # now look in system locations
      FIND_LIBRARY(LIB_EAY_DEBUG NAMES libeay32MDd PATH_SUFFIXES "" Debug)
    ENDIF(NOT LIB_EAY_DEBUG)

    FIND_LIBRARY(LIB_EAY_RELEASE NAMES libeay32MD libeay32 PATH_SUFFIXES "" Release PATHS ${OPENSSL_ROOT} ${OPENSSL_ROOT}/lib
                 NO_DEFAULT_PATH)
    IF(NOT LIB_EAY_RELEASE)  # now look in system locations
      FIND_LIBRARY(LIB_EAY_RELEASE NAMES libeay32MD libeay32 PATH_SUFFIXES "" Release)
    ENDIF(NOT LIB_EAY_RELEASE)

    SET(SSL_EAY_LIBRARY)
    IF(SSL_EAY_DEBUG AND SSL_EAY_RELEASE)
      SET(SSL_EAY_LIBRARY debug ${SSL_EAY_DEBUG} optimized ${SSL_EAY_RELEASE})
    ELSEIF(SSL_EAY_DEBUG)
      SET(SSL_EAY_LIBRARY ${SSL_EAY_DEBUG})
    ELSEIF(SSL_EAY_RELEASE)
      SET(SSL_EAY_LIBRARY ${SSL_EAY_RELEASE})
    ENDIF(SSL_EAY_DEBUG AND SSL_EAY_RELEASE)

    SET(LIB_EAY_LIBRARY)
    IF(LIB_EAY_DEBUG AND LIB_EAY_RELEASE)
      SET(LIB_EAY_LIBRARY debug ${LIB_EAY_DEBUG} optimized ${LIB_EAY_RELEASE})
    ELSEIF(LIB_EAY_DEBUG)
      SET(LIB_EAY_LIBRARY ${LIB_EAY_DEBUG})
    ELSEIF(LIB_EAY_RELEASE)
      SET(LIB_EAY_LIBRARY ${LIB_EAY_RELEASE})
    ENDIF(LIB_EAY_DEBUG AND LIB_EAY_RELEASE)

    SET(OPENSSL_LIBRARIES)
    IF(SSL_EAY_LIBRARY AND LIB_EAY_LIBRARY)
      SET(OPENSSL_LIBRARIES ${SSL_EAY_LIBRARY} ${LIB_EAY_LIBRARY})
    ENDIF(SSL_EAY_LIBRARY AND LIB_EAY_LIBRARY)

    MARK_AS_ADVANCED(SSL_EAY_DEBUG SSL_EAY_RELEASE LIB_EAY_DEBUG LIB_EAY_RELEASE)

  ELSE(WIN32)

    FIND_LIBRARY(OPENSSL_LIBRARIES NAMES ssl ssleay32 ssleay32MD PATHS ${OPENSSL_ROOT} ${OPENSSL_ROOT}/lib NO_DEFAULT_PATH)
    IF(NOT OPENSSL_LIBRARIES)  # now look in system locations
      FIND_LIBRARY(OPENSSL_LIBRARIES NAMES ssl ssleay32 ssleay32MD)
    ENDIF(NOT OPENSSL_LIBRARIES)

    # The Crypto library is not always included in the list of SSL libraries
    FIND_LIBRARY(CRYPTO_LIBRARY NAMES crypto PATHS ${OPENSSL_ROOT} ${OPENSSL_ROOT}/lib NO_DEFAULT_PATH)
    IF(NOT CRYPTO_LIBRARY)  # now look in system locations
      FIND_LIBRARY(CRYPTO_LIBRARY NAMES crypto)
    ENDIF(NOT CRYPTO_LIBRARY)

    IF(NOT CRYPTO_LIBRARY)
      MESSAGE(FATAL_ERROR "OpenSSL Crypto library missing")
    ELSE(NOT CRYPTO_LIBRARY)
      SET(OPENSSL_LIBRARIES ${OPENSSL_LIBRARIES} ${CRYPTO_LIBRARY})
    ENDIF(NOT CRYPTO_LIBRARY)

  ENDIF(WIN32)

  IF(OPENSSL_LIBRARIES)
    SET(OPENSSL_FOUND TRUE)
  ENDIF(OPENSSL_LIBRARIES)
ENDIF(OPENSSL_INCLUDE_DIRS)

IF(OPENSSL_FOUND)
  IF(OPENSSL_FOUND)
    SET(OPENSSL_VERSION)
    SET(OPENSSL_LIB_VERSION)
    FILE(READ "${OPENSSL_INCLUDE_DIRS}/openssl/opensslv.h" _OPENSSLV_H_CONTENTS)

    STRING(REGEX MATCH "#define[ \t]+OPENSSL_VERSION_NUMBER[ \t]+0x[0-9]+" OPENSSL_VERSION "${_OPENSSLV_H_CONTENTS}")
    STRING(REGEX REPLACE ".*0x([0-9][0-9][0-9][0-9][0-9]).*" "\\1" OPENSSL_VERSION "${OPENSSL_VERSION}")

    STRING(REGEX MATCH "#define[ \t]+SHLIB_VERSION_NUMBER[ \t]+\"[0-9.]+" OPENSSL_LIB_VERSION "${_OPENSSLV_H_CONTENTS}")
    STRING(REGEX MATCH "[0-9.]+$" OPENSSL_LIB_VERSION "${OPENSSL_LIB_VERSION}")

    SET(OPENSSL_VERSION ${OPENSSL_VERSION} CACHE INTERNAL "The version number for the OpenSSL library")
    SET(OPENSSL_LIB_VERSION ${OPENSSL_LIB_VERSION} CACHE INTERNAL "The shared library version string for the OpenSSL library")

    IF(OPENSSL_VERSION)
      MATH(EXPR OPENSSL_VERSION_MAJOR "${OPENSSL_VERSION} / 10000")
      MATH(EXPR OPENSSL_VERSION_MINOR "${OPENSSL_VERSION} / 100 % 100")
      MATH(EXPR OPENSSL_VERSION_PATCH "${OPENSSL_VERSION} % 100")
    ENDIF(OPENSSL_VERSION)
  ENDIF(OPENSSL_FOUND)
ENDIF(OPENSSL_FOUND)

IF(OPENSSL_FOUND)
  IF(NOT OPENSSL_FIND_QUIETLY)
    MESSAGE(STATUS "Found OpenSSL: headers at ${OPENSSL_INCLUDE_DIRS}, libraries at ${OPENSSL_LIBRARIES}")
  ENDIF(NOT OPENSSL_FIND_QUIETLY)
ELSE(OPENSSL_FOUND)
	#  IF(OPENSSL_FIND_REQUIRED)
    MESSAGE(FATAL_ERROR "Could not find OpenSSL")
    #  ENDIF(OPENSSL_FIND_REQUIRED)
ENDIF(OPENSSL_FOUND)

MARK_AS_ADVANCED(OPENSSL_INCLUDE_DIR OPENSSL_LIBRARIES OPENSSL_VERSION OPENSSL_LIB_VERSION)
