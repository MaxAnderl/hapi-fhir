package ca.uhn.fhir.rest.param;

/*
 * #%L
 * HAPI FHIR Library
 * %%
 * Copyright (C) 2014 University Health Network
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ca.uhn.fhir.context.ConfigurationException;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.api.SortOrderEnum;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.client.BaseClientInvocation;
import ca.uhn.fhir.rest.method.Request;
import ca.uhn.fhir.rest.server.Constants;
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;

public class SortParameter implements IParameter {

	@Override
	public void translateClientArgumentIntoQueryArgument(FhirContext theContext, Object theSourceClientArgument, Map<String, List<String>> theTargetQueryArguments, BaseClientInvocation theClientInvocation) throws InternalErrorException {
		SortSpec ss = (SortSpec) theSourceClientArgument;
		if (ss ==null) {
			return;
		}
		String name;
		if (ss.getOrder()==null) {
			name = Constants.PARAM_SORT;
		}else if (ss.getOrder() == SortOrderEnum.ASC) {
			name = Constants.PARAM_SORT_ASC;
		}else {
			name = Constants.PARAM_SORT_DESC;
		}
		
		if (ss.getFieldName() != null) {
			if (!theTargetQueryArguments.containsKey(name)) {
				// TODO: implement
			}
		}
		
	}

	@Override
	public Object translateQueryParametersIntoServerArgument(Request theRequest, Object theRequestContents) throws InternalErrorException, InvalidRequestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initializeTypes(Method theMethod, Class<? extends Collection<?>> theOuterCollectionType, Class<? extends Collection<?>> theInnerCollectionType, Class<?> theParameterType) {
		if (theOuterCollectionType != null) {
			throw new ConfigurationException("Method '" + theMethod.getName() + "' in type '" + "' is annotated with @" + Sort.class.getName() + " but can not be of collection type");
		}
		if (!ParameterUtil.getBindableInstantTypes().contains(theParameterType)) {
			throw new ConfigurationException("Method '" + theMethod.getName() + "' in type '" + "' is annotated with @" + Sort.class.getName() + " but is an invalid type, must be: " + SortSpec.class.getCanonicalName());
		}
	}


}
