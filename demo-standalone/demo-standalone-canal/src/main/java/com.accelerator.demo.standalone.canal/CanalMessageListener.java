package com.accelerator.demo.standalone.canal;

import com.alibaba.otter.canal.protocol.FlatMessage;

import java.util.List;

public interface CanalMessageListener {

    void consumeMessages(List<FlatMessage> flatMessages);

}
