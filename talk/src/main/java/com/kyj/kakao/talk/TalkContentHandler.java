/********************************
 *	프로젝트 : talk
 *	패키지   : com.kyj.kakao.talk
 *	작성일   : 2020. 4. 15.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.kakao.talk;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
@FunctionalInterface
public interface TalkContentHandler {

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 15.
	 * @param date
	 * @param time
	 * @param userName
	 * @param content
	 * @return true - stop reading
	 */
	public boolean content(String date, String time, String userName, String content);
}
