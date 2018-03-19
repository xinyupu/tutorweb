package com.pxy.tutor.repitile;

import com.pxy.tutor.domain.entities.MTutor;
import com.pxy.tutor.serivce.MTutorService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TutorReptile {

    private final String url = "http://www.eduease.com/changecity.php";
    private final String host = "http://www.eduease.com/";

    @Autowired
    public MTutorService tutorService;

    public static void main(String[] arg0) {
        new TutorReptile();
    }

    public TutorReptile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                parse();
            }
        }).start();
    }

    private void parse() {
        Document connect = JsoupFactory.connect(url, new JsoupDefaultConfig());
        Elements othercity = connect.getElementsByClass("othercity");
        for (Element element : othercity) {
            Elements dd = element.getElementsByTag("dd");
            for (Element elementTag : dd) {
                Elements a = elementTag.getElementsByTag("a");
                for (Element elementA : a) {
                    String city = elementA.text();
                    String href = elementA.attr("href");
                    String urlChlid=host + href;
                    Document cityChildDocument = JsoupFactory.connect(urlChlid, new JsoupDefaultConfig());
                    Elements leftM1 = cityChildDocument.getElementsByClass("leftM1");
                    for (Element elementChild : leftM1) {
                        String moreUrl = elementChild.getElementsByClass("tit01TB1").get(0).getElementsByTag("a").get(0).attr("abs:href");
                        if (!StringUtils.isEmpty(moreUrl)) {
                            Document moreDocument = JsoupFactory.connect(moreUrl, new JsoupDefaultConfig());
                            Elements tutorElements = moreDocument.getElementsByClass("line03").get(1).getElementsByTag("li");
                            for (Element tutorElement : tutorElements) {
                                Element elementTutorA = tutorElement.getElementsByTag("em").get(0).getElementsByTag("a").get(0);
                                String tutorDetailUrl = elementTutorA.attr("abs:href");
                                Document tutorDetailDocument = JsoupFactory.connect(tutorDetailUrl, new JsoupDefaultConfig());
                                if (tutorDetailDocument != null) {
                                    Elements tbodys = tutorDetailDocument.getElementsByTag("tbody");
                                    Elements trs = tbodys.get(0).getElementsByTag("tr");
                                    try {
                                        String tutorNO = trs.get(1).getElementsByClass("green").get(0).text();
                                        String tutorName = trs.get(2).getElementsByClass("blue").get(0).text();
                                        String birthday = trs.get(4).getElementsByTag("td").get(1).text();
                                        String education = trs.get(5).getElementsByTag("td").get(1).text();
                                        String majorTeach = trs.get(6).getElementsByTag("td").get(1).text();
                                        String adds = trs.get(6).getElementsByTag("td").get(3).text();
                                        Elements tr2 = tbodys.get(1).getElementsByTag("tr");
                                        String canTeach = tr2.get(1).getElementsByTag("td").get(1).text();
                                        String desc = tr2.get(2).getElementsByTag("td").get(1).text();
                                        System.out.println("\n\n" + tutorNO);
                                        System.out.println(tutorName);
                                        System.out.println(birthday);
                                        System.out.println(education);
                                        System.out.println(city + "-" + adds);
                                        System.out.println(majorTeach);
                                        System.out.println(canTeach);
                                        System.out.println(desc);

                                        MTutor tutor = new MTutor();
                                        tutor.setName(tutorName);
                                        tutor.setTutorNO(tutorNO);
                                        tutor.setAddress(city + "-" + adds);
                                        tutor.setBirthday(birthday);
                                        tutor.setCanTeach(canTeach);
                                        tutor.setDescTeach(desc);
                                        tutor.setEducation(education);
                                        tutor.setPwd("123456");
                                        tutor.setMajorTeach(majorTeach);
                                        tutorService.saveTutor(tutor);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }


                                }
                            }
                        }
                    }


                }
            }
        }
    }
}
