// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package natsconnectormodule.proxies.microflows;

import java.util.HashMap;
import java.util.Map;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class Microflows
{
	/**
	 * @deprecated
	 * The default constructor of the Microflows class should not be used.
	 * Use the static microflow invocation methods instead.
	 */
	@java.lang.Deprecated(since = "9.12", forRemoval = true)
	public Microflows() {}

	// These are the microflows for the NatsConnectorModule module
	public static void flowConnect(IContext context)
	{
		Map<java.lang.String, Object> params = new HashMap<>();
		Core.microflowCall("NatsConnectorModule.FlowConnect").withParams(params).execute(context);
	}
	public static java.util.List<natsconnectormodule.proxies.StringWrapper> flowManageGetSubjects(IContext context, java.lang.String _connectionId, java.lang.String _stream, java.lang.String _subjectFilter)
	{
		Map<java.lang.String, Object> params = new HashMap<>();
		params.put("connectionId", _connectionId);
		params.put("stream", _stream);
		params.put("subjectFilter", _subjectFilter);
		java.util.List<IMendixObject> objs = Core.microflowCall("NatsConnectorModule.FlowManageGetSubjects").withParams(params).execute(context);
		if (objs == null) {
			return null;
		} else {
			return objs.stream()
				.map(obj -> natsconnectormodule.proxies.StringWrapper.initialize(context, obj))
				.collect(java.util.stream.Collectors.toList());
		}
	}
}
