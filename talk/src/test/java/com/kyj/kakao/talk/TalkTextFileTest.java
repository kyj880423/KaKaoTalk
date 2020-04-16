/********************************
 *	프로젝트 : talk
 *	패키지   : com.kyj.kakao.talk
 *	작성일   : 2020. 4. 15.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.kakao.talk;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class TalkTextFileTest {

	@Test
	void talkPrintTest() throws URISyntaxException {
		var t = new TalkTextApi(new File(TalkTextFileTest.class.getResource("KakaoTalk.txt").toURI()));
		t.setTalkContentHandler(new TalkContentHandler() {

			@Override
			public boolean content(String date, String time, String userName, String content) {
				System.out.printf(" <아이디> %s <시간> %s <대화> %s\n", userName, date + " " + time, content);
				return false;
			}
		});
		t.parse();
	}

	@Test
	void talkAggregateHandlerTest() throws URISyntaxException {
		var t = new TalkTextApi(new File(TalkTextFileTest.class.getResource("KakaoTalk.txt").toURI()));
		TalkAggregateHandler talkContentHandler = new TalkAggregateHandler();
		t.setTalkContentHandler(talkContentHandler);
		t.parse();

		talkContentHandler.getUserMap()
		.entrySet().stream().sorted((a,b)->{
			return ~Integer.compare(a.getValue(), b.getValue());
		})
		.forEach(e ->{
			System.out.printf("%s %d \n", e.getKey(), e.getValue());
		});
		
		System.out.println("#########################################");
		talkContentHandler.getUserTalks("쪼돼지").forEach(System.out::println);
//		.forEach((u, c) -> {
//			System.out.printf("%s %d \n", u, c);
//		});
	}

}
