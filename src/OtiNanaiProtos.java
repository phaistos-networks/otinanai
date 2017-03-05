// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/oti_nanai_protos.proto

package gr.phaistosnetworks.admin.otinanai;

public final class OtiNanaiProtos {
  private OtiNanaiProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface HistogramOrBuilder extends
      // @@protoc_insertion_point(interface_extends:otinanai.Histogram)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 timestamp = 1;</code>
     */
    int getTimestamp();

    /**
     * <code>float min_value = 2;</code>
     */
    float getMinValue();

    /**
     * <code>float range_step = 3;</code>
     */
    float getRangeStep();

    /**
     * <code>repeated uint32 range_count = 4 [packed = true];</code>
     */
    java.util.List<java.lang.Integer> getRangeCountList();
    /**
     * <code>repeated uint32 range_count = 4 [packed = true];</code>
     */
    int getRangeCountCount();
    /**
     * <code>repeated uint32 range_count = 4 [packed = true];</code>
     */
    int getRangeCount(int index);
  }
  /**
   * Protobuf type {@code otinanai.Histogram}
   */
  public  static final class Histogram extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:otinanai.Histogram)
      HistogramOrBuilder {
    // Use Histogram.newBuilder() to construct.
    private Histogram(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Histogram() {
      timestamp_ = 0;
      minValue_ = 0F;
      rangeStep_ = 0F;
      rangeCount_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private Histogram(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              timestamp_ = input.readInt32();
              break;
            }
            case 21: {

              minValue_ = input.readFloat();
              break;
            }
            case 29: {

              rangeStep_ = input.readFloat();
              break;
            }
            case 32: {
              if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
                rangeCount_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000008;
              }
              rangeCount_.add(input.readUInt32());
              break;
            }
            case 34: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              if (!((mutable_bitField0_ & 0x00000008) == 0x00000008) && input.getBytesUntilLimit() > 0) {
                rangeCount_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000008;
              }
              while (input.getBytesUntilLimit() > 0) {
                rangeCount_.add(input.readUInt32());
              }
              input.popLimit(limit);
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
          rangeCount_ = java.util.Collections.unmodifiableList(rangeCount_);
        }
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.internal_static_otinanai_Histogram_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.internal_static_otinanai_Histogram_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram.class, gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram.Builder.class);
    }

    private int bitField0_;
    public static final int TIMESTAMP_FIELD_NUMBER = 1;
    private int timestamp_;
    /**
     * <code>int32 timestamp = 1;</code>
     */
    public int getTimestamp() {
      return timestamp_;
    }

    public static final int MIN_VALUE_FIELD_NUMBER = 2;
    private float minValue_;
    /**
     * <code>float min_value = 2;</code>
     */
    public float getMinValue() {
      return minValue_;
    }

    public static final int RANGE_STEP_FIELD_NUMBER = 3;
    private float rangeStep_;
    /**
     * <code>float range_step = 3;</code>
     */
    public float getRangeStep() {
      return rangeStep_;
    }

    public static final int RANGE_COUNT_FIELD_NUMBER = 4;
    private java.util.List<java.lang.Integer> rangeCount_;
    /**
     * <code>repeated uint32 range_count = 4 [packed = true];</code>
     */
    public java.util.List<java.lang.Integer>
        getRangeCountList() {
      return rangeCount_;
    }
    /**
     * <code>repeated uint32 range_count = 4 [packed = true];</code>
     */
    public int getRangeCountCount() {
      return rangeCount_.size();
    }
    /**
     * <code>repeated uint32 range_count = 4 [packed = true];</code>
     */
    public int getRangeCount(int index) {
      return rangeCount_.get(index);
    }
    private int rangeCountMemoizedSerializedSize = -1;

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (timestamp_ != 0) {
        output.writeInt32(1, timestamp_);
      }
      if (minValue_ != 0F) {
        output.writeFloat(2, minValue_);
      }
      if (rangeStep_ != 0F) {
        output.writeFloat(3, rangeStep_);
      }
      if (getRangeCountList().size() > 0) {
        output.writeUInt32NoTag(34);
        output.writeUInt32NoTag(rangeCountMemoizedSerializedSize);
      }
      for (int i = 0; i < rangeCount_.size(); i++) {
        output.writeUInt32NoTag(rangeCount_.get(i));
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (timestamp_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, timestamp_);
      }
      if (minValue_ != 0F) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(2, minValue_);
      }
      if (rangeStep_ != 0F) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(3, rangeStep_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < rangeCount_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeUInt32SizeNoTag(rangeCount_.get(i));
        }
        size += dataSize;
        if (!getRangeCountList().isEmpty()) {
          size += 1;
          size += com.google.protobuf.CodedOutputStream
              .computeInt32SizeNoTag(dataSize);
        }
        rangeCountMemoizedSerializedSize = dataSize;
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram)) {
        return super.equals(obj);
      }
      gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram other = (gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram) obj;

      boolean result = true;
      result = result && (getTimestamp()
          == other.getTimestamp());
      result = result && (
          java.lang.Float.floatToIntBits(getMinValue())
          == java.lang.Float.floatToIntBits(
              other.getMinValue()));
      result = result && (
          java.lang.Float.floatToIntBits(getRangeStep())
          == java.lang.Float.floatToIntBits(
              other.getRangeStep()));
      result = result && getRangeCountList()
          .equals(other.getRangeCountList());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
      hash = (53 * hash) + getTimestamp();
      hash = (37 * hash) + MIN_VALUE_FIELD_NUMBER;
      hash = (53 * hash) + java.lang.Float.floatToIntBits(
          getMinValue());
      hash = (37 * hash) + RANGE_STEP_FIELD_NUMBER;
      hash = (53 * hash) + java.lang.Float.floatToIntBits(
          getRangeStep());
      if (getRangeCountCount() > 0) {
        hash = (37 * hash) + RANGE_COUNT_FIELD_NUMBER;
        hash = (53 * hash) + getRangeCountList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code otinanai.Histogram}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:otinanai.Histogram)
        gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.HistogramOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.internal_static_otinanai_Histogram_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.internal_static_otinanai_Histogram_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram.class, gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram.Builder.class);
      }

      // Construct using gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        timestamp_ = 0;

        minValue_ = 0F;

        rangeStep_ = 0F;

        rangeCount_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.internal_static_otinanai_Histogram_descriptor;
      }

      public gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram getDefaultInstanceForType() {
        return gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram.getDefaultInstance();
      }

      public gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram build() {
        gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram buildPartial() {
        gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram result = new gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        result.timestamp_ = timestamp_;
        result.minValue_ = minValue_;
        result.rangeStep_ = rangeStep_;
        if (((bitField0_ & 0x00000008) == 0x00000008)) {
          rangeCount_ = java.util.Collections.unmodifiableList(rangeCount_);
          bitField0_ = (bitField0_ & ~0x00000008);
        }
        result.rangeCount_ = rangeCount_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram) {
          return mergeFrom((gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram other) {
        if (other == gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram.getDefaultInstance()) return this;
        if (other.getTimestamp() != 0) {
          setTimestamp(other.getTimestamp());
        }
        if (other.getMinValue() != 0F) {
          setMinValue(other.getMinValue());
        }
        if (other.getRangeStep() != 0F) {
          setRangeStep(other.getRangeStep());
        }
        if (!other.rangeCount_.isEmpty()) {
          if (rangeCount_.isEmpty()) {
            rangeCount_ = other.rangeCount_;
            bitField0_ = (bitField0_ & ~0x00000008);
          } else {
            ensureRangeCountIsMutable();
            rangeCount_.addAll(other.rangeCount_);
          }
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int timestamp_ ;
      /**
       * <code>int32 timestamp = 1;</code>
       */
      public int getTimestamp() {
        return timestamp_;
      }
      /**
       * <code>int32 timestamp = 1;</code>
       */
      public Builder setTimestamp(int value) {
        
        timestamp_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 timestamp = 1;</code>
       */
      public Builder clearTimestamp() {
        
        timestamp_ = 0;
        onChanged();
        return this;
      }

      private float minValue_ ;
      /**
       * <code>float min_value = 2;</code>
       */
      public float getMinValue() {
        return minValue_;
      }
      /**
       * <code>float min_value = 2;</code>
       */
      public Builder setMinValue(float value) {
        
        minValue_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>float min_value = 2;</code>
       */
      public Builder clearMinValue() {
        
        minValue_ = 0F;
        onChanged();
        return this;
      }

      private float rangeStep_ ;
      /**
       * <code>float range_step = 3;</code>
       */
      public float getRangeStep() {
        return rangeStep_;
      }
      /**
       * <code>float range_step = 3;</code>
       */
      public Builder setRangeStep(float value) {
        
        rangeStep_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>float range_step = 3;</code>
       */
      public Builder clearRangeStep() {
        
        rangeStep_ = 0F;
        onChanged();
        return this;
      }

      private java.util.List<java.lang.Integer> rangeCount_ = java.util.Collections.emptyList();
      private void ensureRangeCountIsMutable() {
        if (!((bitField0_ & 0x00000008) == 0x00000008)) {
          rangeCount_ = new java.util.ArrayList<java.lang.Integer>(rangeCount_);
          bitField0_ |= 0x00000008;
         }
      }
      /**
       * <code>repeated uint32 range_count = 4 [packed = true];</code>
       */
      public java.util.List<java.lang.Integer>
          getRangeCountList() {
        return java.util.Collections.unmodifiableList(rangeCount_);
      }
      /**
       * <code>repeated uint32 range_count = 4 [packed = true];</code>
       */
      public int getRangeCountCount() {
        return rangeCount_.size();
      }
      /**
       * <code>repeated uint32 range_count = 4 [packed = true];</code>
       */
      public int getRangeCount(int index) {
        return rangeCount_.get(index);
      }
      /**
       * <code>repeated uint32 range_count = 4 [packed = true];</code>
       */
      public Builder setRangeCount(
          int index, int value) {
        ensureRangeCountIsMutable();
        rangeCount_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated uint32 range_count = 4 [packed = true];</code>
       */
      public Builder addRangeCount(int value) {
        ensureRangeCountIsMutable();
        rangeCount_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated uint32 range_count = 4 [packed = true];</code>
       */
      public Builder addAllRangeCount(
          java.lang.Iterable<? extends java.lang.Integer> values) {
        ensureRangeCountIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, rangeCount_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated uint32 range_count = 4 [packed = true];</code>
       */
      public Builder clearRangeCount() {
        rangeCount_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000008);
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:otinanai.Histogram)
    }

    // @@protoc_insertion_point(class_scope:otinanai.Histogram)
    private static final gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram();
    }

    public static gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Histogram>
        PARSER = new com.google.protobuf.AbstractParser<Histogram>() {
      public Histogram parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new Histogram(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Histogram> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Histogram> getParserForType() {
      return PARSER;
    }

    public gr.phaistosnetworks.admin.otinanai.OtiNanaiProtos.Histogram getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_otinanai_Histogram_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_otinanai_Histogram_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\032src/oti_nanai_protos.proto\022\010otinanai\"^" +
      "\n\tHistogram\022\021\n\ttimestamp\030\001 \001(\005\022\021\n\tmin_va" +
      "lue\030\002 \001(\002\022\022\n\nrange_step\030\003 \001(\002\022\027\n\013range_c" +
      "ount\030\004 \003(\rB\002\020\001B$\n\"gr.phaistosnetworks.ad" +
      "min.otinanaib\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_otinanai_Histogram_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_otinanai_Histogram_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_otinanai_Histogram_descriptor,
        new java.lang.String[] { "Timestamp", "MinValue", "RangeStep", "RangeCount", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
