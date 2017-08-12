package com.shzhangji.javasandbox.canal;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.protocol.Message;

public class SimpleClient {

    public static void main(String[] args) throws Exception {

        CanalConnector connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress("127.0.0.1", 11111), "example", "", "");

        connector.connect();
        connector.subscribe(".*\\..*");
        connector.rollback();

        while (true) {
            Message message = connector.getWithoutAck(100);
            long batchId = message.getId();
            int size = message.getEntries().size();
            if (batchId == -1 || size == 0) {
                System.out.println("sleep");
                Thread.sleep(3000);
                continue;
            }

            for (Entry entry : message.getEntries()) {
                if (entry.getEntryType() != EntryType.ROWDATA) {
                    continue;
                }
                RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());
                for (RowData rowData : rowChange.getRowDatasList()) {
                    switch (rowChange.getEventType()) {
                    case INSERT:
                    case UPDATE:
                        System.out.print("UPSERT ");
                        printColumns(rowData.getAfterColumnsList());
                        break;

                    case DELETE:
                        System.out.print("DELETE ");
                        printColumns(rowData.getBeforeColumnsList());
                        break;

                    default:
                        break;
                    }
                }
            }

            connector.ack(batchId);
        }
    }

    private static void printColumns(List<Column> columns) {
        String line = columns.stream()
                .map(column -> column.getName() + "=" + column.getValue())
                .collect(Collectors.joining(","));
        System.out.println(line);
    }

}
