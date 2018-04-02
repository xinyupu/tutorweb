package com.pxy.tutor.repitile;

import com.pxy.tutor.domain.entities.MStudent;
import com.pxy.tutor.domain.entities.MTutor;
import com.pxy.tutor.serivce.MStudentService;
import com.pxy.tutor.serivce.MTutorService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class TutorReptile {

    private final String url = "http://www.eduease.com/changecity.php";
    private final String host = "http://www.eduease.com/";
    private String head = "http://www.woyaogexing.com/touxiang/index.html";
    private String nextPage = "";
    private List<String> headUrlList;

    @Autowired
    public MTutorService tutorService;

    @Autowired
    public MStudentService studentService;

    public static void main(String[] arg0) {
        new TutorReptile();
    }

    public TutorReptile() {

        new Thread(new Runnable() {
            @Override
            public void run() {
               /* try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                headUrlList = new ArrayList<>();
                try {
                    parseHead(head);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setHead();*/
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //   parse();
            }
        }).start();
    }

    private void parse() {
        Document connect = JsoupFactory.connect(url, new JsoupDefaultConfig());
        Elements othercity = connect.getElementsByClass("othercity");
        int index = 0;
        for (Element element : othercity) {
            Elements dd = element.getElementsByTag("dd");


            for (Element elementTag : dd) {
                index++;
                if (index > 10) {
                    Elements a = elementTag.getElementsByTag("a");
                    for (Element elementA : a) {
                        String city = elementA.text();
                        String href = elementA.attr("abs:href");
                        if (href != null && !href.equals("")) {
                            Document cityChildDocument = JsoupFactory.connect(href, new JsoupDefaultConfig());
                            if (cityChildDocument != null) {
                                Elements leftM1 = cityChildDocument.getElementsByClass("leftM1");
                                Element element1 = cityChildDocument.getElementsByClass("leftM1").get(1).getElementsByClass("tit01").get(1);
                                pareStudent(element1, city);
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
                                                } catch (Exception e) {
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


        }
    }

    private void pareStudent(final Element element, String city) {

        String moreUrl = element.getElementsByClass("tit01TB1").get(0).getElementsByTag("a").get(0).attr("abs:href");
        Document connect = JsoupFactory.connect(moreUrl, new JsoupDefaultConfig());
        Elements elementsByTag = connect.getElementsByClass("line03").get(0).getElementsByTag("li");
        for (Element li : elementsByTag) {
            Elements a = li.getElementsByTag("a");
            if (a != null && a.size() > 0) {
                String deatailUrl = a.attr("abs:href");
                Document deatailDOC = JsoupFactory.connect(deatailUrl, new JsoupDefaultConfig());
                if (deatailDOC != null) {
                    Elements tbody = deatailDOC.getElementsByTag("tbody");
                    Elements tr = tbody.get(0).getElementsByTag("tr");
                    String studentNO = tr.get(1).getElementsByTag("td").get(1).text();
                    String studentName = tr.get(1).getElementsByTag("td").get(3).text();
                    String studentCondition = tr.get(2).getElementsByTag("td").get(1).text();
                    String requireTeach = tr.get(2).getElementsByTag("td").get(3).text();
                    String areaTeach = tr.get(3).getElementsByTag("td").get(3).text();
                    String studentRequireDesc = tr.get(4).getElementsByTag("td").get(1).text();
                    String teachingTime = tr.get(5).getElementsByTag("td").get(1).text();

                    System.out.println("\n\n" + studentNO);
                    System.out.println(studentName);
                    System.out.println(studentCondition);
                    System.out.println(requireTeach);
                    System.out.println(areaTeach);
                    System.out.println(studentRequireDesc);
                    System.out.println(teachingTime);

                    MStudent student = new MStudent();
                    student.setAreaTeach(city + "-" + areaTeach);
                    student.setHeadUrl("");
                    student.setName(studentName);
                    student.setPwd("123456");
                    student.setRequireTeach(requireTeach);
                    student.setTeachingTime(teachingTime);
                    student.setStudentNO(studentNO);
                    student.setStudentCondition(studentCondition);
                    student.setStudentRequireDesc(studentRequireDesc);
                    studentService.saveStudent(student);
                }

            }

        }

    }

    private void parseHead(String headUrl) {
        Document connect = JsoupFactory.connect(head, new JsoupDefaultConfig());
        Elements elementsByTag = connect.getElementsByClass("pageNum").get(0).getElementsByClass("page").get(0).getElementsByTag("a");
        for (Element ea : elementsByTag) {
            if (ea.text().equals("下一页")) {
                nextPage = ea.attr("abs:href");
            }
        }
        Elements element = connect.getElementsByClass("pMain").get(0).getElementsByClass("txList");
        for (Element e : element) {
            String attr = e.getElementsByTag("img").get(0).attr("src");
            headUrlList.add(attr);
        }
        if (headUrlList.size() < 5000)
            parseHead(nextPage);

    }

    private void setHead() {
        List<MTutor> all = tutorService.getAll();
        for (MTutor m : all) {
            if (m.getHeadUrl() == null) {
                m.setHeadUrl(headUrlList.get(all.indexOf(m)));
                tutorService.updateTutor(m);
            } else if (m.getHeadUrl().equals("")) {
                int random = (int) (Math.random() * headUrlList.size());
                m.setHeadUrl(headUrlList.get(all.indexOf(m)));
                tutorService.updateTutor(m);
            }
        }
    }
}
