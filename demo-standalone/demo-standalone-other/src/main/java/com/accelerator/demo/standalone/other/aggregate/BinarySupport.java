package com.accelerator.demo.standalone.other.aggregate;

import java.io.IOException;

public interface BinarySupport {

    byte[] toBytes() throws IOException;

    void fromBytes(byte[] bytes) throws IOException;

}