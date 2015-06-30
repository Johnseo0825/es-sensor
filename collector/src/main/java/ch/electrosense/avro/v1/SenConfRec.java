/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package ch.electrosense.avro.v1;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class SenConfRec extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SenConfRec\",\"namespace\":\"ch.electrosense.avro.v1\",\"fields\":[{\"name\":\"HoppingStrategy\",\"type\":\"int\",\"doc\":\"Identifier of the hopping strategy used to overcome the bandwidth limitations of the RF front-end. [0:Sequential, 1:Random, 2:Similarity]\"},{\"name\":\"WindowingFunction\",\"type\":\"int\",\"doc\":\"Identifier of the windowing function used to reshape the time-domain samples. [0:Rectangular, 1:Hanning, 2:BlackmanHarris]\"},{\"name\":\"FFTSize\",\"type\":\"int\",\"doc\":\"Size of the fast Fourier transform (FFT), i.e. the number of samples in the frequency-domain representation of a signal. [2^i, where i in {8,...,17}]\"},{\"name\":\"AveragingFactor\",\"type\":\"int\",\"doc\":\"Number of frequency-domain signals to average. [>0]\"},{\"name\":\"FrequencyOverlap\",\"type\":\"float\",\"doc\":\"Fraction of the frequency-domain signals to drop due to non-linear frequency responses of the RF front-end. The effective number of samples in frequency-domain signals is reduced from FFTSize to (1-FrequencyOverlap)*(FFTSize+1). The bandwidth of the frequency-domain signals is reduced from FFTSize*FrequencyResolution to (1-FrequencyOverlap)*FFTSize*FrequencyResolution. [0,...,1]\"},{\"name\":\"FrequencyResolution\",\"type\":\"float\",\"doc\":\"Frequency difference in Hz between successive samples within the frequency-domain signals. [>0]\"},{\"name\":\"Gain\",\"type\":\"float\",\"doc\":\"RF front-end gain in dB. [-1 for automatic gain control]\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /** Identifier of the hopping strategy used to overcome the bandwidth limitations of the RF front-end. [0:Sequential, 1:Random, 2:Similarity] */
  @Deprecated public int HoppingStrategy;
  /** Identifier of the windowing function used to reshape the time-domain samples. [0:Rectangular, 1:Hanning, 2:BlackmanHarris] */
  @Deprecated public int WindowingFunction;
  /** Size of the fast Fourier transform (FFT), i.e. the number of samples in the frequency-domain representation of a signal. [2^i, where i in {8,...,17}] */
  @Deprecated public int FFTSize;
  /** Number of frequency-domain signals to average. [>0] */
  @Deprecated public int AveragingFactor;
  /** Fraction of the frequency-domain signals to drop due to non-linear frequency responses of the RF front-end. The effective number of samples in frequency-domain signals is reduced from FFTSize to (1-FrequencyOverlap)*(FFTSize+1). The bandwidth of the frequency-domain signals is reduced from FFTSize*FrequencyResolution to (1-FrequencyOverlap)*FFTSize*FrequencyResolution. [0,...,1] */
  @Deprecated public float FrequencyOverlap;
  /** Frequency difference in Hz between successive samples within the frequency-domain signals. [>0] */
  @Deprecated public float FrequencyResolution;
  /** RF front-end gain in dB. [-1 for automatic gain control] */
  @Deprecated public float Gain;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public SenConfRec() {}

  /**
   * All-args constructor.
   */
  public SenConfRec(java.lang.Integer HoppingStrategy, java.lang.Integer WindowingFunction, java.lang.Integer FFTSize, java.lang.Integer AveragingFactor, java.lang.Float FrequencyOverlap, java.lang.Float FrequencyResolution, java.lang.Float Gain) {
    this.HoppingStrategy = HoppingStrategy;
    this.WindowingFunction = WindowingFunction;
    this.FFTSize = FFTSize;
    this.AveragingFactor = AveragingFactor;
    this.FrequencyOverlap = FrequencyOverlap;
    this.FrequencyResolution = FrequencyResolution;
    this.Gain = Gain;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return HoppingStrategy;
    case 1: return WindowingFunction;
    case 2: return FFTSize;
    case 3: return AveragingFactor;
    case 4: return FrequencyOverlap;
    case 5: return FrequencyResolution;
    case 6: return Gain;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: HoppingStrategy = (java.lang.Integer)value$; break;
    case 1: WindowingFunction = (java.lang.Integer)value$; break;
    case 2: FFTSize = (java.lang.Integer)value$; break;
    case 3: AveragingFactor = (java.lang.Integer)value$; break;
    case 4: FrequencyOverlap = (java.lang.Float)value$; break;
    case 5: FrequencyResolution = (java.lang.Float)value$; break;
    case 6: Gain = (java.lang.Float)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'HoppingStrategy' field.
   * Identifier of the hopping strategy used to overcome the bandwidth limitations of the RF front-end. [0:Sequential, 1:Random, 2:Similarity]   */
  public java.lang.Integer getHoppingStrategy() {
    return HoppingStrategy;
  }

  /**
   * Sets the value of the 'HoppingStrategy' field.
   * Identifier of the hopping strategy used to overcome the bandwidth limitations of the RF front-end. [0:Sequential, 1:Random, 2:Similarity]   * @param value the value to set.
   */
  public void setHoppingStrategy(java.lang.Integer value) {
    this.HoppingStrategy = value;
  }

  /**
   * Gets the value of the 'WindowingFunction' field.
   * Identifier of the windowing function used to reshape the time-domain samples. [0:Rectangular, 1:Hanning, 2:BlackmanHarris]   */
  public java.lang.Integer getWindowingFunction() {
    return WindowingFunction;
  }

  /**
   * Sets the value of the 'WindowingFunction' field.
   * Identifier of the windowing function used to reshape the time-domain samples. [0:Rectangular, 1:Hanning, 2:BlackmanHarris]   * @param value the value to set.
   */
  public void setWindowingFunction(java.lang.Integer value) {
    this.WindowingFunction = value;
  }

  /**
   * Gets the value of the 'FFTSize' field.
   * Size of the fast Fourier transform (FFT), i.e. the number of samples in the frequency-domain representation of a signal. [2^i, where i in {8,...,17}]   */
  public java.lang.Integer getFFTSize() {
    return FFTSize;
  }

  /**
   * Sets the value of the 'FFTSize' field.
   * Size of the fast Fourier transform (FFT), i.e. the number of samples in the frequency-domain representation of a signal. [2^i, where i in {8,...,17}]   * @param value the value to set.
   */
  public void setFFTSize(java.lang.Integer value) {
    this.FFTSize = value;
  }

  /**
   * Gets the value of the 'AveragingFactor' field.
   * Number of frequency-domain signals to average. [>0]   */
  public java.lang.Integer getAveragingFactor() {
    return AveragingFactor;
  }

  /**
   * Sets the value of the 'AveragingFactor' field.
   * Number of frequency-domain signals to average. [>0]   * @param value the value to set.
   */
  public void setAveragingFactor(java.lang.Integer value) {
    this.AveragingFactor = value;
  }

  /**
   * Gets the value of the 'FrequencyOverlap' field.
   * Fraction of the frequency-domain signals to drop due to non-linear frequency responses of the RF front-end. The effective number of samples in frequency-domain signals is reduced from FFTSize to (1-FrequencyOverlap)*(FFTSize+1). The bandwidth of the frequency-domain signals is reduced from FFTSize*FrequencyResolution to (1-FrequencyOverlap)*FFTSize*FrequencyResolution. [0,...,1]   */
  public java.lang.Float getFrequencyOverlap() {
    return FrequencyOverlap;
  }

  /**
   * Sets the value of the 'FrequencyOverlap' field.
   * Fraction of the frequency-domain signals to drop due to non-linear frequency responses of the RF front-end. The effective number of samples in frequency-domain signals is reduced from FFTSize to (1-FrequencyOverlap)*(FFTSize+1). The bandwidth of the frequency-domain signals is reduced from FFTSize*FrequencyResolution to (1-FrequencyOverlap)*FFTSize*FrequencyResolution. [0,...,1]   * @param value the value to set.
   */
  public void setFrequencyOverlap(java.lang.Float value) {
    this.FrequencyOverlap = value;
  }

  /**
   * Gets the value of the 'FrequencyResolution' field.
   * Frequency difference in Hz between successive samples within the frequency-domain signals. [>0]   */
  public java.lang.Float getFrequencyResolution() {
    return FrequencyResolution;
  }

  /**
   * Sets the value of the 'FrequencyResolution' field.
   * Frequency difference in Hz between successive samples within the frequency-domain signals. [>0]   * @param value the value to set.
   */
  public void setFrequencyResolution(java.lang.Float value) {
    this.FrequencyResolution = value;
  }

  /**
   * Gets the value of the 'Gain' field.
   * RF front-end gain in dB. [-1 for automatic gain control]   */
  public java.lang.Float getGain() {
    return Gain;
  }

  /**
   * Sets the value of the 'Gain' field.
   * RF front-end gain in dB. [-1 for automatic gain control]   * @param value the value to set.
   */
  public void setGain(java.lang.Float value) {
    this.Gain = value;
  }

  /** Creates a new SenConfRec RecordBuilder */
  public static ch.electrosense.avro.v1.SenConfRec.Builder newBuilder() {
    return new ch.electrosense.avro.v1.SenConfRec.Builder();
  }
  
  /** Creates a new SenConfRec RecordBuilder by copying an existing Builder */
  public static ch.electrosense.avro.v1.SenConfRec.Builder newBuilder(ch.electrosense.avro.v1.SenConfRec.Builder other) {
    return new ch.electrosense.avro.v1.SenConfRec.Builder(other);
  }
  
  /** Creates a new SenConfRec RecordBuilder by copying an existing SenConfRec instance */
  public static ch.electrosense.avro.v1.SenConfRec.Builder newBuilder(ch.electrosense.avro.v1.SenConfRec other) {
    return new ch.electrosense.avro.v1.SenConfRec.Builder(other);
  }
  
  /**
   * RecordBuilder for SenConfRec instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<SenConfRec>
    implements org.apache.avro.data.RecordBuilder<SenConfRec> {

    private int HoppingStrategy;
    private int WindowingFunction;
    private int FFTSize;
    private int AveragingFactor;
    private float FrequencyOverlap;
    private float FrequencyResolution;
    private float Gain;

    /** Creates a new Builder */
    private Builder() {
      super(ch.electrosense.avro.v1.SenConfRec.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(ch.electrosense.avro.v1.SenConfRec.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.HoppingStrategy)) {
        this.HoppingStrategy = data().deepCopy(fields()[0].schema(), other.HoppingStrategy);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.WindowingFunction)) {
        this.WindowingFunction = data().deepCopy(fields()[1].schema(), other.WindowingFunction);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.FFTSize)) {
        this.FFTSize = data().deepCopy(fields()[2].schema(), other.FFTSize);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.AveragingFactor)) {
        this.AveragingFactor = data().deepCopy(fields()[3].schema(), other.AveragingFactor);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.FrequencyOverlap)) {
        this.FrequencyOverlap = data().deepCopy(fields()[4].schema(), other.FrequencyOverlap);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.FrequencyResolution)) {
        this.FrequencyResolution = data().deepCopy(fields()[5].schema(), other.FrequencyResolution);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.Gain)) {
        this.Gain = data().deepCopy(fields()[6].schema(), other.Gain);
        fieldSetFlags()[6] = true;
      }
    }
    
    /** Creates a Builder by copying an existing SenConfRec instance */
    private Builder(ch.electrosense.avro.v1.SenConfRec other) {
            super(ch.electrosense.avro.v1.SenConfRec.SCHEMA$);
      if (isValidValue(fields()[0], other.HoppingStrategy)) {
        this.HoppingStrategy = data().deepCopy(fields()[0].schema(), other.HoppingStrategy);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.WindowingFunction)) {
        this.WindowingFunction = data().deepCopy(fields()[1].schema(), other.WindowingFunction);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.FFTSize)) {
        this.FFTSize = data().deepCopy(fields()[2].schema(), other.FFTSize);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.AveragingFactor)) {
        this.AveragingFactor = data().deepCopy(fields()[3].schema(), other.AveragingFactor);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.FrequencyOverlap)) {
        this.FrequencyOverlap = data().deepCopy(fields()[4].schema(), other.FrequencyOverlap);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.FrequencyResolution)) {
        this.FrequencyResolution = data().deepCopy(fields()[5].schema(), other.FrequencyResolution);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.Gain)) {
        this.Gain = data().deepCopy(fields()[6].schema(), other.Gain);
        fieldSetFlags()[6] = true;
      }
    }

    /** Gets the value of the 'HoppingStrategy' field */
    public java.lang.Integer getHoppingStrategy() {
      return HoppingStrategy;
    }
    
    /** Sets the value of the 'HoppingStrategy' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder setHoppingStrategy(int value) {
      validate(fields()[0], value);
      this.HoppingStrategy = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'HoppingStrategy' field has been set */
    public boolean hasHoppingStrategy() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'HoppingStrategy' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder clearHoppingStrategy() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'WindowingFunction' field */
    public java.lang.Integer getWindowingFunction() {
      return WindowingFunction;
    }
    
    /** Sets the value of the 'WindowingFunction' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder setWindowingFunction(int value) {
      validate(fields()[1], value);
      this.WindowingFunction = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'WindowingFunction' field has been set */
    public boolean hasWindowingFunction() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'WindowingFunction' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder clearWindowingFunction() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'FFTSize' field */
    public java.lang.Integer getFFTSize() {
      return FFTSize;
    }
    
    /** Sets the value of the 'FFTSize' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder setFFTSize(int value) {
      validate(fields()[2], value);
      this.FFTSize = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'FFTSize' field has been set */
    public boolean hasFFTSize() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'FFTSize' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder clearFFTSize() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'AveragingFactor' field */
    public java.lang.Integer getAveragingFactor() {
      return AveragingFactor;
    }
    
    /** Sets the value of the 'AveragingFactor' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder setAveragingFactor(int value) {
      validate(fields()[3], value);
      this.AveragingFactor = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'AveragingFactor' field has been set */
    public boolean hasAveragingFactor() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'AveragingFactor' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder clearAveragingFactor() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'FrequencyOverlap' field */
    public java.lang.Float getFrequencyOverlap() {
      return FrequencyOverlap;
    }
    
    /** Sets the value of the 'FrequencyOverlap' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder setFrequencyOverlap(float value) {
      validate(fields()[4], value);
      this.FrequencyOverlap = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'FrequencyOverlap' field has been set */
    public boolean hasFrequencyOverlap() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'FrequencyOverlap' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder clearFrequencyOverlap() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'FrequencyResolution' field */
    public java.lang.Float getFrequencyResolution() {
      return FrequencyResolution;
    }
    
    /** Sets the value of the 'FrequencyResolution' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder setFrequencyResolution(float value) {
      validate(fields()[5], value);
      this.FrequencyResolution = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'FrequencyResolution' field has been set */
    public boolean hasFrequencyResolution() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'FrequencyResolution' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder clearFrequencyResolution() {
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'Gain' field */
    public java.lang.Float getGain() {
      return Gain;
    }
    
    /** Sets the value of the 'Gain' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder setGain(float value) {
      validate(fields()[6], value);
      this.Gain = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'Gain' field has been set */
    public boolean hasGain() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'Gain' field */
    public ch.electrosense.avro.v1.SenConfRec.Builder clearGain() {
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    public SenConfRec build() {
      try {
        SenConfRec record = new SenConfRec();
        record.HoppingStrategy = fieldSetFlags()[0] ? this.HoppingStrategy : (java.lang.Integer) defaultValue(fields()[0]);
        record.WindowingFunction = fieldSetFlags()[1] ? this.WindowingFunction : (java.lang.Integer) defaultValue(fields()[1]);
        record.FFTSize = fieldSetFlags()[2] ? this.FFTSize : (java.lang.Integer) defaultValue(fields()[2]);
        record.AveragingFactor = fieldSetFlags()[3] ? this.AveragingFactor : (java.lang.Integer) defaultValue(fields()[3]);
        record.FrequencyOverlap = fieldSetFlags()[4] ? this.FrequencyOverlap : (java.lang.Float) defaultValue(fields()[4]);
        record.FrequencyResolution = fieldSetFlags()[5] ? this.FrequencyResolution : (java.lang.Float) defaultValue(fields()[5]);
        record.Gain = fieldSetFlags()[6] ? this.Gain : (java.lang.Float) defaultValue(fields()[6]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}