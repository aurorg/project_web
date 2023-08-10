/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opengoofy.assault.messageservice.biz.algorithm;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.opengoofy.assault.framework.starter.distributedid.SnowflakeIdUtil;

import java.util.*;

/**
 * 自定义分片算法
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public final class SnowflakeDateShardingAlgorithm implements ComplexKeysShardingAlgorithm<Date> {
    
    private final String messageSendId = "msg_id";
    
    private final String sendTime = "create_time";
    
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        Map<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
        Map<String, Range<Comparable<?>>> columnNameAndRangeValuesMap = shardingValue.getColumnNameAndRangeValuesMap();
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        if (CollUtil.isNotEmpty(columnNameAndShardingValuesMap)) {
            Collection<Comparable<?>> sendTimeCollection = columnNameAndShardingValuesMap.get(sendTime);
            if (CollUtil.isNotEmpty(sendTimeCollection)) {
                Comparable<?> comparable = sendTimeCollection.stream().findFirst().get();
                String actualTable = ShardModel.quarterlyModel(logicTableName, (Date) comparable);
                result.add(actualTable);
            } else {
                Collection<Comparable<?>> messageSendIdCollection = columnNameAndShardingValuesMap.get(messageSendId);
                Comparable<?> comparable = messageSendIdCollection.stream().findFirst().get();
                Long snowflakeId;
                if (comparable instanceof String) {
                    snowflakeId = Long.parseLong((String) comparable);
                } else if (comparable instanceof Long) {
                    snowflakeId = (Long) comparable;
                } else {
                    throw new RuntimeException("消息ID类型输入错误，请检查");
                }
                String actualTable = ShardModel.quarterlyModel(logicTableName, new Date(SnowflakeIdUtil.parseSnowflakeId(snowflakeId).getTimestamp()));
                result.add(actualTable);
            }
        } else {
            Range<Comparable<?>> sendTimeRange = columnNameAndRangeValuesMap.get(sendTime);
            if (sendTimeRange != null) {
                Set<String> actualTables = ShardModel.calculateRange(logicTableName, (Date) sendTimeRange.lowerEndpoint(), (Date) sendTimeRange.upperEndpoint());
                result.addAll(actualTables);
            } else {
                result.addAll(availableTargetNames);
            }
        }
        return result;
    }
    
    @Override
    public Properties getProps() {
        return null;
    }
    
    @Override
    public void init(Properties properties) {
        
    }
    
    @Override
    public String getType() {
        return "CLASS_BASED";
    }
}
