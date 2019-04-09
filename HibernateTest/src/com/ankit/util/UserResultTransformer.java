package com.ankit.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.transform.ResultTransformer;

import com.ankit.dto.User;

public class UserResultTransformer  implements ResultTransformer{

	@Override
	public List<User> transformList(@SuppressWarnings("rawtypes") List arg0) {
		System.out.println("MyResultTransformer.transformList()");
		Object tempArray[];
		User tempUser;
		List<User> users=new ArrayList<User>();
		for (Object object : arg0) {
			/*tempArray=(Object[])object;
			tempUser=new User((Integer)tempArray[0], (String)tempArray[1],(String) tempArray[2], (String)tempArray[3]);*/
			users.add((User)object);
		}
		return users;
	}

	@Override
	public Object transformTuple(Object[] arg0, String[] arg1) {
		return 	new User((Integer)arg0[0], (String)arg0[1],(String) arg0[2], (String)arg0[3]);
		
	}

}
