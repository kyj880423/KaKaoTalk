/********************************
 *	프로젝트 : talk
 *	패키지   : com.kyj.kakao.talk
 *	작성일   : 2020. 4. 16.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.kakao.talk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class TalkAggregateHandler implements TalkContentHandler{

	private Map<String, Integer> userMap = new TreeMap<>();
	private Map<String, List<String>> userTalks = new HashMap<>();
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kyj.kakao.talk.TalkContentHandler#content(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean content(String date, String time, String userName, String content) {
		add(userName);
		add(userName, content);
		return false;
	}

	public void add(String userName, String content) {
		if (userTalks.containsKey(userName))
		{
			userTalks.get(userName).add(content);
		}
		else {
			ArrayList<String> value = new ArrayList<>();
			value.add(content);
			userTalks.put(userName, value);
		}
	}
	public void add(String userName) {
		if (userMap.containsKey(userName))
		{
			userMap.put(userName, userMap.get(userName) + 1);
			
		}
		else {
			userMap.put(userName, 1);
		}
	}

	/**
	 * @return the userMap
	 */
	public Map<String, Integer> getUserMap() {
		return userMap;
	}

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 16. 
	 * @param userName
	 * @return
	 */
	public List<String> getUserTalks(String userName){
		List<String> list = this.userTalks.get(userName);
		if(list == null)
			return Collections.emptyList();
		return list;
	}
//	@Override
//	public int compare(String o1, String o2) {
//		Integer x = userMap.get(o1);
//		Integer y = userMap.get(o2);
//		if(x == null)
//			x = 0;
//		if(y == null)
//			y = 0;
//		return Integer.compare(x, y);
//	}

}
