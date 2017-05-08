// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PunterRequest.proto

package ru.splat.messages;

public final class PunterRequest {
  private PunterRequest() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface PunterOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Punter)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 punter_id = 1;</code>
     */
    int getPunterId();

    /**
     * <code>optional int32 local_task = 2;</code>
     */
    int getLocalTask();

    /**
     * <code>repeated int32 services = 3;</code>
     */
    java.util.List<java.lang.Integer> getServicesList();
    /**
     * <code>repeated int32 services = 3;</code>
     */
    int getServicesCount();
    /**
     * <code>repeated int32 services = 3;</code>
     */
    int getServices(int index);

    /**
     * <code>optional int64 time = 4;</code>
     */
    long getTime();
  }
  /**
   * Protobuf type {@code Punter}
   */
  public  static final class Punter extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Punter)
      PunterOrBuilder {
    // Use Punter.newBuilder() to construct.
    private Punter(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Punter() {
      punterId_ = 0;
      localTask_ = 0;
      services_ = java.util.Collections.emptyList();
      time_ = 0L;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private Punter(
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

              punterId_ = input.readInt32();
              break;
            }
            case 16: {

              localTask_ = input.readInt32();
              break;
            }
            case 24: {
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
                services_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000004;
              }
              services_.add(input.readInt32());
              break;
            }
            case 26: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004) && input.getBytesUntilLimit() > 0) {
                services_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000004;
              }
              while (input.getBytesUntilLimit() > 0) {
                services_.add(input.readInt32());
              }
              input.popLimit(limit);
              break;
            }
            case 32: {

              time_ = input.readInt64();
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
        if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
          services_ = java.util.Collections.unmodifiableList(services_);
        }
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ru.splat.messages.PunterRequest.internal_static_Punter_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ru.splat.messages.PunterRequest.internal_static_Punter_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ru.splat.messages.PunterRequest.Punter.class, ru.splat.messages.PunterRequest.Punter.Builder.class);
    }

    private int bitField0_;
    public static final int PUNTER_ID_FIELD_NUMBER = 1;
    private int punterId_;
    /**
     * <code>optional int32 punter_id = 1;</code>
     */
    public int getPunterId() {
      return punterId_;
    }

    public static final int LOCAL_TASK_FIELD_NUMBER = 2;
    private int localTask_;
    /**
     * <code>optional int32 local_task = 2;</code>
     */
    public int getLocalTask() {
      return localTask_;
    }

    public static final int SERVICES_FIELD_NUMBER = 3;
    private java.util.List<java.lang.Integer> services_;
    /**
     * <code>repeated int32 services = 3;</code>
     */
    public java.util.List<java.lang.Integer>
        getServicesList() {
      return services_;
    }
    /**
     * <code>repeated int32 services = 3;</code>
     */
    public int getServicesCount() {
      return services_.size();
    }
    /**
     * <code>repeated int32 services = 3;</code>
     */
    public int getServices(int index) {
      return services_.get(index);
    }
    private int servicesMemoizedSerializedSize = -1;

    public static final int TIME_FIELD_NUMBER = 4;
    private long time_;
    /**
     * <code>optional int64 time = 4;</code>
     */
    public long getTime() {
      return time_;
    }

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
      if (punterId_ != 0) {
        output.writeInt32(1, punterId_);
      }
      if (localTask_ != 0) {
        output.writeInt32(2, localTask_);
      }
      if (getServicesList().size() > 0) {
        output.writeUInt32NoTag(26);
        output.writeUInt32NoTag(servicesMemoizedSerializedSize);
      }
      for (int i = 0; i < services_.size(); i++) {
        output.writeInt32NoTag(services_.get(i));
      }
      if (time_ != 0L) {
        output.writeInt64(4, time_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (punterId_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, punterId_);
      }
      if (localTask_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, localTask_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < services_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(services_.get(i));
        }
        size += dataSize;
        if (!getServicesList().isEmpty()) {
          size += 1;
          size += com.google.protobuf.CodedOutputStream
              .computeInt32SizeNoTag(dataSize);
        }
        servicesMemoizedSerializedSize = dataSize;
      }
      if (time_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(4, time_);
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
      if (!(obj instanceof ru.splat.messages.PunterRequest.Punter)) {
        return super.equals(obj);
      }
      ru.splat.messages.PunterRequest.Punter other = (ru.splat.messages.PunterRequest.Punter) obj;

      boolean result = true;
      result = result && (getPunterId()
          == other.getPunterId());
      result = result && (getLocalTask()
          == other.getLocalTask());
      result = result && getServicesList()
          .equals(other.getServicesList());
      result = result && (getTime()
          == other.getTime());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + PUNTER_ID_FIELD_NUMBER;
      hash = (53 * hash) + getPunterId();
      hash = (37 * hash) + LOCAL_TASK_FIELD_NUMBER;
      hash = (53 * hash) + getLocalTask();
      if (getServicesCount() > 0) {
        hash = (37 * hash) + SERVICES_FIELD_NUMBER;
        hash = (53 * hash) + getServicesList().hashCode();
      }
      hash = (37 * hash) + TIME_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTime());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static ru.splat.messages.PunterRequest.Punter parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ru.splat.messages.PunterRequest.Punter parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ru.splat.messages.PunterRequest.Punter parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ru.splat.messages.PunterRequest.Punter parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ru.splat.messages.PunterRequest.Punter parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ru.splat.messages.PunterRequest.Punter parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static ru.splat.messages.PunterRequest.Punter parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static ru.splat.messages.PunterRequest.Punter parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static ru.splat.messages.PunterRequest.Punter parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ru.splat.messages.PunterRequest.Punter parseFrom(
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
    public static Builder newBuilder(ru.splat.messages.PunterRequest.Punter prototype) {
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
     * Protobuf type {@code Punter}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Punter)
        ru.splat.messages.PunterRequest.PunterOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ru.splat.messages.PunterRequest.internal_static_Punter_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ru.splat.messages.PunterRequest.internal_static_Punter_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                ru.splat.messages.PunterRequest.Punter.class, ru.splat.messages.PunterRequest.Punter.Builder.class);
      }

      // Construct using ru.splat.messages.PunterRequest.Punter.newBuilder()
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
        punterId_ = 0;

        localTask_ = 0;

        services_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        time_ = 0L;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ru.splat.messages.PunterRequest.internal_static_Punter_descriptor;
      }

      public ru.splat.messages.PunterRequest.Punter getDefaultInstanceForType() {
        return ru.splat.messages.PunterRequest.Punter.getDefaultInstance();
      }

      public ru.splat.messages.PunterRequest.Punter build() {
        ru.splat.messages.PunterRequest.Punter result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public ru.splat.messages.PunterRequest.Punter buildPartial() {
        ru.splat.messages.PunterRequest.Punter result = new ru.splat.messages.PunterRequest.Punter(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        result.punterId_ = punterId_;
        result.localTask_ = localTask_;
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          services_ = java.util.Collections.unmodifiableList(services_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.services_ = services_;
        result.time_ = time_;
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
        if (other instanceof ru.splat.messages.PunterRequest.Punter) {
          return mergeFrom((ru.splat.messages.PunterRequest.Punter)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(ru.splat.messages.PunterRequest.Punter other) {
        if (other == ru.splat.messages.PunterRequest.Punter.getDefaultInstance()) return this;
        if (other.getPunterId() != 0) {
          setPunterId(other.getPunterId());
        }
        if (other.getLocalTask() != 0) {
          setLocalTask(other.getLocalTask());
        }
        if (!other.services_.isEmpty()) {
          if (services_.isEmpty()) {
            services_ = other.services_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureServicesIsMutable();
            services_.addAll(other.services_);
          }
          onChanged();
        }
        if (other.getTime() != 0L) {
          setTime(other.getTime());
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
        ru.splat.messages.PunterRequest.Punter parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (ru.splat.messages.PunterRequest.Punter) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int punterId_ ;
      /**
       * <code>optional int32 punter_id = 1;</code>
       */
      public int getPunterId() {
        return punterId_;
      }
      /**
       * <code>optional int32 punter_id = 1;</code>
       */
      public Builder setPunterId(int value) {
        
        punterId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 punter_id = 1;</code>
       */
      public Builder clearPunterId() {
        
        punterId_ = 0;
        onChanged();
        return this;
      }

      private int localTask_ ;
      /**
       * <code>optional int32 local_task = 2;</code>
       */
      public int getLocalTask() {
        return localTask_;
      }
      /**
       * <code>optional int32 local_task = 2;</code>
       */
      public Builder setLocalTask(int value) {
        
        localTask_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 local_task = 2;</code>
       */
      public Builder clearLocalTask() {
        
        localTask_ = 0;
        onChanged();
        return this;
      }

      private java.util.List<java.lang.Integer> services_ = java.util.Collections.emptyList();
      private void ensureServicesIsMutable() {
        if (!((bitField0_ & 0x00000004) == 0x00000004)) {
          services_ = new java.util.ArrayList<java.lang.Integer>(services_);
          bitField0_ |= 0x00000004;
         }
      }
      /**
       * <code>repeated int32 services = 3;</code>
       */
      public java.util.List<java.lang.Integer>
          getServicesList() {
        return java.util.Collections.unmodifiableList(services_);
      }
      /**
       * <code>repeated int32 services = 3;</code>
       */
      public int getServicesCount() {
        return services_.size();
      }
      /**
       * <code>repeated int32 services = 3;</code>
       */
      public int getServices(int index) {
        return services_.get(index);
      }
      /**
       * <code>repeated int32 services = 3;</code>
       */
      public Builder setServices(
          int index, int value) {
        ensureServicesIsMutable();
        services_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 services = 3;</code>
       */
      public Builder addServices(int value) {
        ensureServicesIsMutable();
        services_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 services = 3;</code>
       */
      public Builder addAllServices(
          java.lang.Iterable<? extends java.lang.Integer> values) {
        ensureServicesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, services_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 services = 3;</code>
       */
      public Builder clearServices() {
        services_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
        return this;
      }

      private long time_ ;
      /**
       * <code>optional int64 time = 4;</code>
       */
      public long getTime() {
        return time_;
      }
      /**
       * <code>optional int64 time = 4;</code>
       */
      public Builder setTime(long value) {
        
        time_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 time = 4;</code>
       */
      public Builder clearTime() {
        
        time_ = 0L;
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


      // @@protoc_insertion_point(builder_scope:Punter)
    }

    // @@protoc_insertion_point(class_scope:Punter)
    private static final ru.splat.messages.PunterRequest.Punter DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ru.splat.messages.PunterRequest.Punter();
    }

    public static ru.splat.messages.PunterRequest.Punter getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Punter>
        PARSER = new com.google.protobuf.AbstractParser<Punter>() {
      public Punter parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new Punter(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Punter> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Punter> getParserForType() {
      return PARSER;
    }

    public ru.splat.messages.PunterRequest.Punter getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Punter_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Punter_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023PunterRequest.proto\"O\n\006Punter\022\021\n\tpunte" +
      "r_id\030\001 \001(\005\022\022\n\nlocal_task\030\002 \001(\005\022\020\n\010servic" +
      "es\030\003 \003(\005\022\014\n\004time\030\004 \001(\003B\"\n\021ru.splat.messa" +
      "gesB\rPunterRequestb\006proto3"
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
    internal_static_Punter_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Punter_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Punter_descriptor,
        new java.lang.String[] { "PunterId", "LocalTask", "Services", "Time", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}