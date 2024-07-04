package com.shzhangji.flink;

import org.apache.flink.table.data.binary.BinaryStringData;
import org.apache.flink.table.data.GenericRowData;
import org.apache.flink.table.runtime.typeutils.ExternalSerializer;
import org.apache.flink.table.runtime.dataview.StateMapView;
import org.apache.flink.table.api.dataview.MapView;
import org.apache.flink.table.data.binary.BinaryRawValueData;
import org.apache.flink.table.runtime.typeutils.StringDataSerializer;
import org.apache.flink.table.data.RowData;
import org.apache.flink.table.runtime.dataview.StateDataViewStore;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.table.runtime.generated.AggsHandleFunction;

public final class GroupAggsHandler implements AggsHandleFunction {
  long aggCount;
  boolean aggCountIsNull;
  private transient ExternalSerializer keySerializer;
  private transient ExternalSerializer valueSerializer;
  private StateMapView distinctAcc_0_dataview;
  private MapView<BinaryStringData, Long> distinctView;
  private transient StringDataSerializer typeSerializer;
  GenericRowData aggValue = new GenericRowData(1);

  private StateDataViewStore store;

  public GroupAggsHandler(Object[] references) throws Exception {
    keySerializer = (ExternalSerializer) references[0];
    valueSerializer = (ExternalSerializer) references[1];
    typeSerializer = (StringDataSerializer) references[2];
  }

  private RuntimeContext getRuntimeContext() {
    return store.getRuntimeContext();
  }

  @Override
  public void open(StateDataViewStore store) throws Exception {
    this.store = store;
    distinctAcc_0_dataview = store.getStateMapView("distinctAcc_0", true, keySerializer, valueSerializer);
    distinctView = distinctAcc_0_dataview;
  }

  @Override
  public void setWindowSize(int windowSize) {

  }

  @Override
  public void accumulate(RowData accInput) throws Exception {
    BinaryStringData field;
    boolean fieldIsNull;
    BinaryStringData fieldCopy;

    fieldIsNull = accInput.isNullAt(1);
    field = BinaryStringData.EMPTY_UTF8;
    if (!fieldIsNull) {
      field = ((BinaryStringData) accInput.getString(1));
    }

    fieldCopy = field;
    if (!fieldIsNull) {
      fieldCopy = (BinaryStringData) (typeSerializer.copy(fieldCopy));
    }

    BinaryStringData distinctKey = fieldCopy;
    if (fieldIsNull) {
      distinctKey = null;
    }

    Long distinctValue = distinctView.get(distinctKey);
    if (distinctValue == null) {
      distinctValue = 0L;
    }

    boolean isDistinctValueChanged = false;

    long existed = ((long) distinctValue) & (1L << 0);
    if (existed == 0) {  // not existed
      distinctValue = ((long) distinctValue) | (1L << 0);
      isDistinctValueChanged = true;

      long aggCountResult = -1L;
      boolean resultIsNull;
      if (fieldIsNull) {
        resultIsNull = aggCountIsNull;
        if (!aggCountIsNull) {
          aggCountResult = aggCount;
        }
      }
      else {
        long count = -1L;
        if (!aggCountIsNull) {
          count = (long) (aggCount + ((long) 1L));
        }
        resultIsNull = aggCountIsNull;
        if (!resultIsNull) {
          aggCountResult = count;
        }
      }
      aggCount = aggCountResult;
      aggCountIsNull = resultIsNull;
    }

    if (isDistinctValueChanged) {
      distinctView.put(distinctKey, distinctValue);
    }
  }

  @Override
  public void retract(RowData retractInput) throws Exception {
    throw new RuntimeException("This function not require retract method, but the retract method is called.");
  }

  @Override
  public void merge(RowData otherAcc) throws Exception {
    throw new RuntimeException("This function not require merge method, but the merge method is called.");
  }

  @Override
  public void setAccumulators(RowData acc) throws Exception {
    long aggCountResult;
    boolean resultIsNull;
    resultIsNull = acc.isNullAt(0);
    aggCountResult = -1L;
    if (!resultIsNull) {
      aggCountResult = acc.getLong(0);
    }

    distinctView = distinctAcc_0_dataview;
    aggCount = aggCountResult;
    aggCountIsNull = resultIsNull;
  }

  @Override
  public void resetAccumulators() throws Exception {
    aggCount = ((long) 0L);
    aggCountIsNull = false;
    distinctView.clear();
  }

  @Override
  public RowData getAccumulators() throws Exception {
    GenericRowData acc = new GenericRowData(2);

    if (aggCountIsNull) {
      acc.setField(0, null);
    } else {
      acc.setField(0, aggCount);
    }

    BinaryRawValueData<MapView<BinaryStringData, Long>> distinctAcc = BinaryRawValueData.fromObject(distinctView);
    acc.setField(1, distinctAcc);
    return acc;
  }

  @Override
  public RowData createAccumulators() throws Exception {
    GenericRowData acc = new GenericRowData(2);
    acc.setField(0, 0L);

    MapView<BinaryStringData, Long> mapView = new MapView<>();
    BinaryRawValueData<MapView<BinaryStringData, Long>> distinctAcc = BinaryRawValueData.fromObject(mapView);
    acc.setField(1, distinctAcc);
    return acc;
  }

  @Override
  public RowData getValue() throws Exception {
    aggValue = new GenericRowData(1);

    if (aggCountIsNull) {
      aggValue.setField(0, null);
    } else {
      aggValue.setField(0, aggCount);
    }

    return aggValue;
  }

  @Override
  public void cleanup() throws Exception {
    distinctAcc_0_dataview.clear();
  }

  @Override
  public void close() throws Exception {

  }
}
