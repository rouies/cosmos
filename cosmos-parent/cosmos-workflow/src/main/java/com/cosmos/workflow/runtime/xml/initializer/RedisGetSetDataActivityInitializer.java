package com.cosmos.workflow.runtime.xml.initializer;

import org.dom4j.Element;

import com.cosmos.utils.text.StringUtils;
import com.cosmos.workflow.activities.sequence.action.redis.RedisGetSetDataActivity;
import com.cosmos.workflow.activities.sequence.action.redis.STRING_TYPE;
import com.cosmos.workflow.runtime.WorkflowRuntimeException;
import com.cosmos.workflow.runtime.xml.IXmlInitializer;

public class RedisGetSetDataActivityInitializer implements IXmlInitializer<RedisGetSetDataActivity>{

	@Override
	public void init(RedisGetSetDataActivity activity, Element item)
			throws WorkflowRuntimeException {
		String client = item.attributeValue("client");
		String key = item.attributeValue("key");
		String type = item.attributeValue("type");
		String out = item.attributeValue("out");
		String value = item.attributeValue("value");
		if(StringUtils.isEmptyOrNull(client) ||
			StringUtils.isEmptyOrNull(key) ||
			StringUtils.isEmptyOrNull(value)){
			throw new WorkflowRuntimeException("必填项不能为空");
		}
		if(StringUtils.isEmptyOrNull(type) || type.equals("STRING")){
			activity.setType(STRING_TYPE.STRING);
		} else {
			activity.setType(STRING_TYPE.BINARY);
		}
		activity.setValue(value);
		activity.setClient(client);
		activity.setKey(key);
		activity.setOut(out);
	}

}
