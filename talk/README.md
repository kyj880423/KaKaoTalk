카카오톡 대화 처리기.

Example)



--톡내용을 모두 출력

var t = new TalkTextApi(new File(TalkTextFileTest.class.getResource("KakaoTalk.txt").toURI()));
t.setTalkContentHandler(new TalkContentHandler() {

@Override
public boolean content(String date, String time, String userName, String content) {
	System.out.printf(" <아이디> %s <시간> %s <대화> %s\n", userName, date + " " + time, content);
	return false;
}
});
t.parse();
	
	
--톡 내용이 많은 사용자 출력 및 대화내용출력

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
