package com.sps.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import bean.privilege.Privilege;

public class PrivilegeUtil {
	public static Set<Integer> getprivilegeIds(int role_id) {
		Set<Integer> p_ids = new HashSet<Integer>();
		int temp = 1, p_id;
		while (role_id > 0) {
			p_id = (role_id % 2) * temp;
			if (p_id != 0){
				System.out.println("pid: "+p_id);
				p_ids.add(p_id);
			}
			role_id = role_id / 2;
			temp = temp << 1;
		}
		return p_ids;
	}
	
	public static Map<Integer, Privilege> getPrivilegeMap(Set<Integer> p_ids){
		Map<Integer, Privilege> privilegeMap = new HashMap<Integer, Privilege>();
		
		return privilegeMap;
	}
}
