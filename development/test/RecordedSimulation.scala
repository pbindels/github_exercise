package test

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://pespn.chartbeat.net")
		.inferHtmlResources()

	val headers_0 = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")

	val headers_1 = Map("Accept" -> "*/*")

	val headers_23 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Content-Type" -> "text/plain;charset=UTF-8")

	val headers_26 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_47 = Map(
		"Accept" -> "*/*",
		"Origin" -> "http://espn.go.com")

	val headers_58 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"X-Requested-With" -> "XMLHttpRequest")

    val uri01 = "google.com"
    val uri02 = "js-agent.newrelic.com"
    val uri03 = "static.chartbeat.com"
    val uri04 = "http://pespn.chartbeat.net/ping"
    val uri05 = "http://a3.espncdn.com/combiner/i"
    val uri06 = "http://images.outbrain.com/imageserver/v2/s"
    val uri07 = "www.googletagservices.com"
    val uri08 = "fan.api.espn.go.com"
    val uri09 = "assets.espn.go.com"
    val uri10 = "http://cdn.espn.go.com/core"
    val uri11 = "http://b.scorecardresearch.com"
    val uri12 = "secure-us.imrworldwide.com"
    val uri13 = "http://a1.espncdn.com/combiner/i"
    val uri14 = "http://widgets.outbrain.com"
    val uri15 = "http://a2.espncdn.com/combiner/i"
    val uri16 = "odb.outbrain.com"
    val uri17 = "zn_4slwdofs4ykbmhc-espn.siteintercept.qualtrics.com"
    val uri18 = "http://log.outbrain.com/loggerServices/widgetGlobalEvent"
    val uri19 = "https://www.google.com:443"
    val uri20 = "http://fastcast.espn.com/public/rotationip"
    val uri21 = "http://bam.nr-data.net/1/7c16f39f8d"
    val uri22 = "http://espn.go.com"
    val uri23 = "http://a4.espncdn.com/combiner/i"
    val uri24 = "cdn.optimizely.com"
    val uri25 = "http://a.espncdn.com"
    val uri26 = "w88.go.com"
    val uri27 = "tredir.go.com"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("http://" + uri01 + "/")
			.headers(headers_0)
			.resources(http("request_1")
			.get(uri19 + "/xjs/_/js/k=xjs.s.en_US.9E5IFDIe0XY.O/m=sx,c,sb,cdos,cr,elog,jsa,r,hsm,j,p,d,csi/am=kCKCIOI7DwJhhVETpCIQsRg/rt=j/d=1/t=zcms/rs=ACT90oElZ5NlwjDaDCwaJHpPotGsUxJlmA")
			.headers(headers_1),
            http("request_2")
			.get(uri19 + "/textinputassistant/tia.png"),
            http("request_3")
			.get(uri19 + "/xjs/_/js/k=xjs.s.en_US.TWlzWReDiPg.O/m=abd,sy59,sy58,sy57,sy60,async,erh,sy61,foot,fpe,idck,ifl,ipv6,lc,sy31,sy119,lu,m,sf,sy34,sy40,sy122,sy123,sy143,sy35,sy41,sy38,sy106,sy142,sy144,sy94,sy118,sy112,sy115,sy121,sy131,sy145,sy124,sy215,sy141,sy152,sy132,sy211,sy248,sy265,sy39,sy90,em13,em3,em6,em4,em5,em9,em7,em10,sy266,skp,vm/am=kCKCIOI7DwJhhVETpCIQsRg/rt=j/d=0/t=zcms/rs=ACT90oHBQVnLgG-w2KDHnUCXFp75XaROqw")
			.headers(headers_1),
            http("request_4")
			.get(uri19 + "/extern_chrome/a76cd6f296423596.js?bav=on.2,or.r_cp.")
			.headers(headers_1),
            http("request_5")
			.get(uri19 + "/s?sclient=psy-ab&site=&source=hp&q=&oq=&gs_l=&pbx=1&bav=on.2,or.r_cp.&fp=1&biw=1920&bih=673&dpr=1&pf=p&gs_rn=64&gs_ri=psy-ab&tok=pjTrYDrcKl5E9KjfshPqTg&cp=0&gs_id=1&xhr=t&tch=1&ech=1&psi=0r4eVpBhi7WiBLiMq-gD.1444855506438.1")
			.headers(headers_0),
            http("request_6")
			.get(uri19 + "/gen_204?v=3&s=webhp&atyp=csi&imc=4&imn=4&imp=4&ei=0r4eVpBhi7WiBLiMq-gD&e=3700288,4026240,4029815,4031109,4031139,4032677,4033307,4033344,4034882,4036527,4037333,4037569,4037688,4038012,4038214,4041440,4041507,4041776,4041837,4042492,4043030,4043203,4043255,4043457,4043458,4043492,4043568,4043596,4043805,4044246,4044606,4045292,4045421,4045717,4045830,4045841,4045872,4046023,4046043,4046187,4046400,4046717,4046835,4046837,4046904,4047130,4047317,4047478,4047530,4047593,4047600,4047602,4048007,4048011,4048093,4048141,4048201,4048371,4048512,4048561,4048570,4048651,4048854,4048909,4048980,4049130,4049252,4049466,4050156,4050325,8300096,8300227,8300236,8300261,8502095,8502221,8502312,8502315,8502444,10200083,10201411&adh=&xjs=init.24.22.sb.16.p.3.ifl.2.jsa.1.abd.1&ima=1&rt=xjsls.23,prt.25,iml.143,dcl.57,xjses.160,xjsee.211,xjs.211,ol.470,aft.143,wsrt.10176,cst.0,dnst.0,rqst.260,rspt.276,rqstt.9903,unt.9815,cstt.9815,dit.76")))
		.pause(8)
		.exec(http("request_7")
			.get(uri19 + "/s?sclient=psy-ab&site=&source=hp&q=b&oq=&gs_l=&pbx=1&bav=on.2,or.r_cp.&bvm=bv.105039540,d.cGU&fp=a76cd6f296423596&biw=1920&bih=673&dpr=1&pf=p&gs_rn=64&gs_ri=psy-ab&tok=pjTrYDrcKl5E9KjfshPqTg&cp=1&gs_id=p8&xhr=t&tch=1&ech=2&psi=0r4eVpBhi7WiBLiMq-gD.1444855506438.1")
			.headers(headers_0)
			.resources(http("request_8")
			.get(uri19 + "/gen_204?atyp=i&ct=1&cad=1&rsm=6&ei=0r4eVpBhi7WiBLiMq-gD&zx=1444855515677")))
		.pause(1)
		.exec(http("request_9")
			.get(uri19 + "/s?sclient=psy-ab&site=&source=hp&q=s&oq=&gs_l=&pbx=1&bav=on.2,or.r_cp.&bvm=bv.105039540,d.cGU&fp=a76cd6f296423596&biw=1920&bih=673&dpr=1&pf=p&gs_rn=64&gs_ri=psy-ab&tok=pjTrYDrcKl5E9KjfshPqTg&cp=1&gs_id=td&xhr=t&tch=1&ech=3&psi=0r4eVpBhi7WiBLiMq-gD.1444855506438.1")
			.headers(headers_0)
			.resources(http("request_10")
			.get(uri19 + "/gen_204?atyp=i&ct=1&cad=1&rsm=6&ei=0r4eVpBhi7WiBLiMq-gD&zx=1444855517127"),
            http("request_11")
			.get(uri19 + "/s?sclient=psy-ab&site=&source=hp&q=sf&oq=&gs_l=&pbx=1&bav=on.2,or.r_cp.&bvm=bv.105039540,d.cGU&fp=a76cd6f296423596&biw=1920&bih=673&dpr=1&pf=p&gs_rn=64&gs_ri=psy-ab&tok=pjTrYDrcKl5E9KjfshPqTg&cp=2&gs_id=u3&xhr=t&tch=1&ech=4&psi=0r4eVpBhi7WiBLiMq-gD.1444855506438.1")
			.headers(headers_0),
            http("request_12")
			.get(uri19 + "/s?sclient=psy-ab&site=&source=hp&q=sf%20&oq=&gs_l=&pbx=1&bav=on.2,or.r_cp.&bvm=bv.105039540,d.cGU&fp=a76cd6f296423596&biw=1920&bih=673&dpr=1&pf=p&gs_rn=64&gs_ri=psy-ab&tok=pjTrYDrcKl5E9KjfshPqTg&cp=3&gs_id=vt&xhr=t&tch=1&ech=5&psi=0r4eVpBhi7WiBLiMq-gD.1444855506438.1")
			.headers(headers_0),
            http("request_13")
			.get(uri19 + "/s?sclient=psy-ab&site=&source=hp&q=sf%20gi&oq=&gs_l=&pbx=1&bav=on.2,or.r_cp.&bvm=bv.105039540,d.cGU&fp=a76cd6f296423596&biw=1920&bih=673&dpr=1&pf=p&gs_rn=64&gs_ri=psy-ab&tok=pjTrYDrcKl5E9KjfshPqTg&cp=5&gs_id=wu&xhr=t&tch=1&ech=7&psi=0r4eVpBhi7WiBLiMq-gD.1444855506438.1")
			.headers(headers_0),
            http("request_14")
			.get(uri19 + "/s?sclient=psy-ab&site=&source=hp&q=sf%20gia&oq=&gs_l=&pbx=1&bav=on.2,or.r_cp.&bvm=bv.105039540,d.cGU&fp=a76cd6f296423596&biw=1920&bih=673&dpr=1&pf=p&gs_rn=64&gs_ri=psy-ab&tok=pjTrYDrcKl5E9KjfshPqTg&cp=6&gs_id=x5&xhr=t&tch=1&ech=8&psi=0r4eVpBhi7WiBLiMq-gD.1444855506438.1")
			.headers(headers_0),
            http("request_15")
			.get(uri19 + "/s?sclient=psy-ab&site=&source=hp&q=sf%20gian&oq=&gs_l=&pbx=1&bav=on.2,or.r_cp.&bvm=bv.105039540,d.cGU&fp=a76cd6f296423596&biw=1920&bih=673&dpr=1&pf=p&gs_rn=64&gs_ri=psy-ab&tok=pjTrYDrcKl5E9KjfshPqTg&cp=7&gs_id=xq&xhr=t&tch=1&ech=9&psi=0r4eVpBhi7WiBLiMq-gD.1444855506438.1")
			.headers(headers_0),
            http("request_16")
			.get(uri19 + "/s?sclient=psy-ab&site=&source=hp&q=sf%20giant&oq=&gs_l=&pbx=1&bav=on.2,or.r_cp.&bvm=bv.105039540,d.cGU&fp=a76cd6f296423596&biw=1920&bih=673&dpr=1&pf=p&gs_rn=64&gs_ri=psy-ab&tok=pjTrYDrcKl5E9KjfshPqTg&cp=8&gs_id=y4&xhr=t&tch=1&ech=10&psi=0r4eVpBhi7WiBLiMq-gD.1444855506438.1")
			.headers(headers_0),
            http("request_17")
			.get(uri19 + "/s?sclient=psy-ab&site=&source=hp&q=sf%20giants&oq=&gs_l=&pbx=1&bav=on.2,or.r_cp.&bvm=bv.105039540,d.cGU&fp=a76cd6f296423596&biw=1920&bih=673&dpr=1&pf=p&gs_rn=64&gs_ri=psy-ab&tok=pjTrYDrcKl5E9KjfshPqTg&cp=9&gs_id=yk&xhr=t&tch=1&ech=11&psi=0r4eVpBhi7WiBLiMq-gD.1444855506438.1")
			.headers(headers_0),
            http("request_18")
			.get(uri19 + "/s?sclient=psy-ab&site=&source=hp&q=sf%20g&oq=&gs_l=&pbx=1&bav=on.2,or.r_cp.&bvm=bv.105039540,d.cGU&fp=a76cd6f296423596&biw=1920&bih=673&dpr=1&pf=p&gs_rn=64&gs_ri=psy-ab&tok=pjTrYDrcKl5E9KjfshPqTg&cp=4&gs_id=wg&xhr=t&tch=1&ech=6&psi=0r4eVpBhi7WiBLiMq-gD.1444855506438.1")
			.headers(headers_0),
            http("request_19")
			.get(uri19 + "/xjs/_/js/k=xjs.s.en_US.TWlzWReDiPg.O/m=aspn,crd,sy4,sy164,sy405,sy406,sy6,sy407,sy429,sy2,sy430,dvl,sy69,sy70,sy72,sy74,sy71,sy75,sy77,sy73,sy83,sy78,sy84,sy158,sy160,sy161,sy159,sy379,sy156,sy378,sy380,lr,lrli,vs,sy65,sy67,sy62,sy68,sy76,sy63,tnv,kx,sy178,sy36,sy347,sy381,lrlb,tabs,sy86,sy87,hov,sy89,vpt,sy79,sy234,sy33,sy346,fc,twt,me,sy353,sy359,sy237,sy369,sy240,sy371,kptm,ilg,scco,sy314,d3l/am=kCKCIOI7DwJhhVETpCIQsRg/rt=j/d=0/t=zcms/rs=ACT90oHBQVnLgG-w2KDHnUCXFp75XaROqw")
			.headers(headers_1),
            http("request_20")
			.get(uri19 + "/gen_204?v=3&s=web&atyp=csi&e=3700288,4026240,4029815,4031109,4031139,4032677,4033307,4033344,4034882,4036527,4037333,4037569,4037688,4038012,4038214,4041440,4041507,4041776,4041837,4042492,4043030,4043203,4043255,4043457,4043458,4043492,4043568,4043596,4043805,4044246,4044606,4045292,4045421,4045717,4045830,4045841,4045872,4046023,4046043,4046187,4046400,4046717,4046835,4046837,4046904,4047130,4047317,4047478,4047530,4047593,4047600,4047602,4048007,4048011,4048093,4048141,4048201,4048371,4048512,4048561,4048570,4048651,4048854,4048909,4048980,4049130,4049252,4049466,4050156,4050325,8300096,8300227,8300236,8300261,8502095,8502221,8502312,8502315,8502444,10200083,10201411&ei=3r4eVp_kAoyyogTA3ZDYBw&cr=c&imp=45&pf=1&pfa=n.10,ttfc.68,ttlc.0,cbt.100&pfm=n.10,ttfc.799,ttlc.0,cbt.100&pmd=max.15,avg.1,1,0,2,0,0,0,1,0,0,15,1,0,1,1,0,1,0,0,1,1&imn=48&adh=&xjs=dispose.3.11.ifl.1.fpe.1.jsa.1.m.0.lu.0,init.14.22.fpe.9.sb.1.cdos.1.async.1.foot.1&ima=15&rt=prt.153,pprt.153,ol.153,jsrt.804,iml.200,aft.199")))
		.pause(2)
		.exec(http("request_21")
			.get(uri19 + "/gen_204?atyp=i&ct=1&cad=1&sqi=3&q=sf%20giants&oq=sf%20giants&gs_l=hp.10..0i131l2j0l2.9103.12113.0.14720.12.8.0.4.4.0.800.1297.5j2j6-1.8.0....0...1c.1.64.psy-ab..3.9.424.0.GDPkvc6S2l0&ei=3r4eVp_kAoyyogTA3ZDYBw&zx=1444855521179"))
		.pause(3)
		.exec(http("request_22")
			.get(uri19 + "/url?sa=t&rct=j&q=&esrc=s&source=web&cd=2&sqi=2&ved=0CDIQqQIwAWoVChMIn8uen-rCyAIVDJmICh3ALgR7&url=http%3A%2F%2Fespn.go.com%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&usg=AFQjCNGnNS1khTWR82LH6GFzmAMyWNTslg&bvm=bv.105039540,d.cGU")
			.headers(headers_0)
			.resources(http("request_23")
			.post(uri19 + "/gen_204?atyp=i&ct=slh&cad=&ei=3r4eVp_kAoyyogTA3ZDYBw&m=HV&t=O&s=1&me=1:1444855519041,x:337,V,0,0,1920,673:0,E,27,136,172,512,291:0,E,47,136,486,512,255:0,E,48,136,514,512,100:0,E,153,702,172,424,797:0,E,154,704,172,454,780:0,E,157,1007,172,151,151:0,E,163,704,184,454,111:0,E,168,719,309,278,81:0,E,197,719,596,424,228:3862,H,168,o:89,H,154,o:0,H,153,o:47,H,47,i:51,H,47,o:239,H,47,i:79,H,48,i:1234,D,0,1,0,48:96,e,C:15,x&v=2&pv=0.165147761930727&zx=1444855525142")
			.headers(headers_23),
            http("request_24")
			.get(uri22 + "/mlb/story/_/id/13838249/san-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings")
			.headers(headers_0),
            http("request_25")
			.get(uri25 + "/redesign/0.274.8/js/espn-head.js")
			.headers(headers_1),
            http("request_26")
			.get(uri25 + "/redesign/0.274.8/css/story.css")
			.headers(headers_26),
            http("request_27")
			.get(uri25 + "/favicon.ico")
			.headers(headers_0),
            http("request_28")
			.get(uri25 + "/redesign/0.274.8/css/page.css")
			.headers(headers_26),
            http("request_29")
			.get(uri25 + "/redesign/0.274.8/js/espn-critical.js")
			.headers(headers_1),
            http("request_30")
			.get("http://" + uri24 + "/js/310987714.js")
			.headers(headers_1),
            http("request_31")
			.get(uri25 + "/redesign/fonts/base64-woff-v5.css")
			.headers(headers_26),
            http("request_32")
			.get(uri25 + "/combiner/i?img=/i/teamlogos/leagues/500/mlb.png&w=80&h=80&transparent=true"),
            http("request_33")
			.get(uri25 + "/combiner/i?img=/i/espn/networks_shows/500/ustandings_mlb.png?w=220&h=220&transparent=true"),
            http("request_34")
			.get(uri25 + "/redesign/0.274.8/assets/img/logos/logo-espn-82x20.png"),
            http("request_35")
			.get(uri25 + "/redesign/0.274.8/js/espn-defer-low.js")
			.headers(headers_1),
            http("request_36")
			.get(uri25 + "/combiner/i?img=/i/columnists/sarris_eno_m.jpg&w=65&h=65&scale=crop"),
            http("request_37")
			.get(uri25 + "/redesign/0.274.8/js/espn-analytics.js")
			.headers(headers_1),
            http("request_38")
			.get(uri25 + "/redesign/0.274.8/js/espn-defer.js")
			.headers(headers_1),
            http("request_39")
			.get(uri25 + "/combiner/i?img=/i/teamlogos/mlb/500/sfo.png&h=150&w=150"),
            http("request_40")
			.get(uri13 + "?img=/photo/2014/1030/mlb_u_posey_bumgarner_jv_1296x729.jpg&h=402&scale=crop&w=1006&location=origin"),
            http("request_41")
			.get("http://" + uri07 + "/tag/js/gpt.js")
			.headers(headers_1),
            http("request_42")
			.get("http://" + uri27 + "/capmon/GetDE?set=j&param=country&param=countryisocode&param=state&param=metro&param=metrocode&param=postcode&param=offset&param=dst&param=countrycode&param=connection")
			.headers(headers_1),
            http("request_43")
			.get(uri25 + "/ad/doubleclick/ads.js")
			.headers(headers_1),
            http("request_44")
			.get(uri25 + "/js/omniture/tracking.js")
			.headers(headers_1),
            http("request_45")
			.get(uri22 + "/core/l?a=false&t=false")
			.headers(headers_0),
            http("request_46")
			.get("http://" + uri08 + "/apis/v2/recommendations/postalCode/10025?callback=onRecommendations&limit=5&displayEvents=true&displayNow=true")
			.headers(headers_1),
            http("request_47")
			.get(uri10 + "/api/v0/nav/index?&device=desktop")
			.headers(headers_47),
            http("request_48")
			.get(uri14 + "/outbrain.js")
			.headers(headers_1),
            http("request_49")
			.get(uri25 + "/combiner/i?img=/i/teamlogos/leagues/500/mlb.png?w=80&h=80&transparent=true"),
            http("request_50")
			.get(uri25 + "/redesign/0.274.8/assets/img/icons/shim.gif"),
            http("request_51")
			.get("http://" + uri16 + "/utils/get?url=http%3A%2F%2Fespn.go.com%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&settings=true&recs=true&widgetJSId=AR_15&key=NANOWDGT01&idx=0&version=288799&ref=https%3A%2F%2Fwww.google.com&apv=false&sig=F3Qd0944&format=html&rand=42255&winW=1920&winH=673")
			.headers(headers_1),
            http("request_52")
			.get(uri10 + "/blog/sweetspot/post/_/id/64539/game-5-previews-blue-jays-royals-favored-at-home?render=true&partial=article&xhr=1&device=desktop")
			.headers(headers_47),
            http("request_53")
			.get(uri06 + "/1jyh/a/yPEPs/34QS4/8rUU/yPEPs-xrm-230x130.jpg"),
            http("request_54")
			.get(uri14 + "/images/widgetIcons/ob_logo_67x12.png"),
            http("request_55")
			.get(uri06 + "/CVQh/a/10RIXi/2XFS7/27BK/10RIXi-xrm-230x130.jpg"),
            http("request_56")
			.get(uri06 + "/MguP/a/1Du5x7/36FWn/9I9F/1Du5x7-xrm-230x130.jpg"),
            http("request_57")
			.get(uri14 + "/nanoWidget/3rd/comScore/comScore.htm")
			.headers(headers_0),
            http("request_58")
			.get(uri22 + "/socialstats/rest/v1/facebook/stats?url=http%3A%2F%2Fes.pn%2F1VSBF9q")
			.headers(headers_58),
            http("request_59")
			.get(uri25 + "/combiner/i?img=/i/columnists/schoenfield_david_m.jpg&w=65&h=65&scale=crop"),
            http("request_60")
			.get(uri11 + "/beacon.js")
			.headers(headers_1),
            http("request_61")
			.get(uri13 + "?img=/media/motion/2015/1014/dm_151014_Stark_predictions/dm_151014_Stark_predictions.jpg&h=530&scale=crop&w=943&location=origin"),
            http("request_62")
			.get(uri13 + "?img=/i/teamlogos/mlb/500/scoreboard/kc.png&h=20&w=20"),
            http("request_63")
			.get(uri13 + "?img=/i/teamlogos/mlb/500/scoreboard/tex.png&h=20&w=20"),
            http("request_64")
			.get(uri13 + "?img=/i/teamlogos/mlb/500/scoreboard/hou.png&h=20&w=20"),
            http("request_65")
			.get(uri13 + "?img=/i/teamlogos/mlb/500/scoreboard/tor.png&h=20&w=20"),
            http("request_66")
			.get(uri23 + "?img=%2Fmedia%2Fmotion%2F2015%2F1014%2Fdm_151014_Stark_predictions%2Fdm_151014_Stark_predictions.jpg&w=78&h=78&scale=crop&cquality=90&location=center"),
            http("request_67")
			.get(uri18 + "?tm=790&pid=643&sid=2787525&wId=174&wRV=288799&rId=217f273f081de27884810e70f782f16a&eT=0&idx=0&pvId=217f273f081de27884810e70f782f16a&org=0&pad=3&pVis=1&eIdx=&ab=0&wl=0")
			.headers(headers_1),
            http("request_68")
			.get(uri11 + "/b?c1=7&c2=14320224&c3=000&ns__t=1444855528398&ns_c=windows-1252&c8=&c7=http%3A%2F%2Fwidgets.outbrain.com%2FnanoWidget%2F3rd%2FcomScore%2FcomScore.htm%23pid%3D643&c9=http%3A%2F%2Fespn.go.com%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings"),
            http("request_69")
			.get(uri05 + "?img=%2Fphoto%2F2014%2F1030%2Fmlb_u_posey_bumgarner_jv_1296x1296.jpg&w=78&h=78&scale=crop&cquality=90&location=center"),
            http("request_70")
			.get(uri25 + "/combiner/i?img=%2Fphoto%2F2015%2F1005%2Fr14297_1218x1218_1%2D1.jpg&w=78&h=78&scale=crop&cquality=90&location=center"),
            http("request_71")
			.get(uri05 + "?img=%2Fphoto%2F2015%2F1009%2F2015_mlb_postseason_1296x1296.png&w=78&h=78&scale=crop&cquality=90&location=center"),
            http("request_72")
			.get(uri05 + "?img=%2Fmedia%2Fmotion%2F2015%2F1014%2Fdm_151014_mlb_otl_talkback_cubs_win%2Fdm_151014_mlb_otl_talkback_cubs_win.jpg&w=78&h=78&scale=crop&cquality=90&location=center"),
            http("request_73")
			.get("http://" + uri17 + "/WRSiteInterceptEngine/?Q_SIID=SI_9EvK9r52GPmgW9v&Q_LOC=http%3A%2F%2Fespn.go.com%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&t=1444855529249")
			.headers(headers_1),
            http("request_74")
			.get(uri05 + "?img=%2Fmedia%2Fmotion%2F2015%2F1013%2Fdm_151013_mlb_schiling_cubs_analysis%2Fdm_151013_mlb_schiling_cubs_analysis.jpg&w=78&h=78&scale=crop&cquality=90&location=center"),
            http("request_75")
			.get(uri15 + "?img=%2Fmedia%2Fmotion%2F2015%2F1014%2Fdm_151014_schwarber_hr%2Fdm_151014_schwarber_hr.jpg&w=78&h=78&scale=crop&cquality=90&location=center"),
            http("request_76")
			.get(uri15 + "?img=%2Fphoto%2F2015%2F1013%2Fr16741_1296x1296_1%2D1.jpg&w=78&h=78&scale=crop&cquality=90&location=center"),
            http("request_77")
			.get(uri15 + "?img=%2Fphoto%2F2015%2F1013%2Fr16791_1296x1296_1%2D1.jpg&w=78&h=78&scale=crop&cquality=90&location=center"),
            http("request_78")
			.get("http://" + uri02 + "/nr-686.min.js")
			.headers(headers_1)
			.check(status.is(304)),
            http("request_79")
			.get(uri21 + "?a=13093470&pl=1444855525143&v=686.b365e66&to=NVxTZBFZXUFQW0ZQVwwWZEIKF15eUxdBTVcQQA%3D%3D&ap=133&be=539&fe=3562&dc=678&f=%5B%5D&perf=%7B%22timing%22:%7B%22of%22:1444855525143,%22n%22:0,%22dl%22:519,%22di%22:1174,%22ds%22:1216,%22de%22:1576,%22dc%22:4100,%22l%22:4100,%22le%22:4108,%22f%22:4,%22dn%22:4,%22dne%22:4,%22c%22:4,%22ce%22:4,%22rq%22:4,%22rp%22:518,%22rpe%22:518%7D,%22navigation%22:%7B%7D%7D&ja=%7B%22pagetype%22:%22story%22,%22sport%22:%22mlb%22,%22device%22:%22desktop%22,%22page-css%22:386,%22head-js%22:218,%22critical-js%22:390,%22connection%22:%22broadband%22%7D&jsonp=NREUM.setToken")
			.headers(headers_1),
            http("request_80")
			.get(uri25 + "/combiner/i?img=%2Fmedia%2Fmotion%2F2015%2F1013%2Fdm_151013_theo_epstein%2Fdm_151013_theo_epstein.jpg&w=78&h=78&scale=crop&cquality=90&location=center"),
            http("request_81")
			.get(uri13 + "?img=%2Fmedia%2Fmotion%2F2015%2F1014%2Fdm_151013_SC_Mets_Dodgers_Highlight269%2Fdm_151013_SC_Mets_Dodgers_Highlight269.jpg&w=78&h=78&scale=crop&cquality=90&location=center")))
		.pause(1)
		.exec(http("request_82")
			.get("http://" + uri03 + "/js/chartbeat_video.js")
			.headers(headers_1)
			.resources(http("request_83")
			.get(uri04 + "?h=espn.go.com&p=%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&u=CgrkLrDuuYnKD5SMKM&d=espn.go.com&g=22222&g0=mlb&g1=story&n=1&f=00001&c=0&x=145&m=145&y=7615&o=1920&w=673&j=45&R=1&W=0&I=0&E=0&e=0&r=https%3A%2F%2Fwww.google.com%2F&t=p2IhuD3_JpF85OHk81UD8CwP20c&V=66&i=San%20Francisco%20Giants%20earn%20MLB%27s%20best%20coach%2C%20players%20and%20stadium%20marks%20in%20Ultimate%20Standings&tz=420&sn=1&_")))
		.pause(9)
		.exec(http("request_84")
			.get(uri20 + "")
			.headers(headers_47))
		.pause(5)
		.exec(http("request_85")
			.get("/ping?h=espn.go.com&p=%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&u=CgrkLrDuuYnKD5SMKM&d=espn.go.com&g=22222&g0=mlb&g1=story&n=1&f=00001&c=0.25&x=1121&m=1121&y=7615&o=1920&w=673&j=30&R=1&W=0&I=0&E=15&e=15&r=https%3A%2F%2Fwww.google.com%2F&t=p2IhuD3_JpF85OHk81UD8CwP20c&V=66&tz=420&sn=2&eS=7b7f&eM=0200&_"))
		.pause(14)
		.exec(http("request_86")
			.get("/ping?h=espn.go.com&p=%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&u=CgrkLrDuuYnKD5SMKM&d=espn.go.com&g=22222&g0=mlb&g1=story&n=1&f=00001&c=0.5&x=1738&m=1738&y=7615&o=1920&w=673&j=30&R=1&W=0&I=0&E=30&e=15&r=https%3A%2F%2Fwww.google.com%2F&t=p2IhuD3_JpF85OHk81UD8CwP20c&V=66&tz=420&sn=3&eS=7d3f&eM=01c0&_"))
		.pause(1)
		.exec(http("request_87")
			.get(uri20 + "")
			.headers(headers_47))
		.pause(13)
		.exec(http("request_88")
			.get("/ping?h=espn.go.com&p=%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&u=CgrkLrDuuYnKD5SMKM&d=espn.go.com&g=22222&g0=mlb&g1=story&n=1&f=00001&c=0.75&x=1744&m=1744&y=7615&o=1920&w=673&j=30&R=1&W=0&I=0&E=34&e=4&r=https%3A%2F%2Fwww.google.com%2F&t=p2IhuD3_JpF85OHk81UD8CwP20c&V=66&tz=420&sn=4&eS=0001&_"))
		.pause(9)
		.exec(http("request_89")
			.get(uri20 + "")
			.headers(headers_47))
		.pause(5)
		.exec(http("request_90")
			.get("/ping?h=espn.go.com&p=%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&u=CgrkLrDuuYnKD5SMKM&d=espn.go.com&g=22222&g0=mlb&g1=story&n=1&f=00001&c=1&x=1930&m=1930&y=7615&o=1920&w=673&j=30&R=1&W=0&I=0&E=49&e=15&r=https%3A%2F%2Fwww.google.com%2F&t=p2IhuD3_JpF85OHk81UD8CwP20c&V=66&tz=420&sn=5&eS=68ff&_"))
		.pause(14)
		.exec(http("request_91")
			.get("/ping?h=espn.go.com&p=%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&u=CgrkLrDuuYnKD5SMKM&d=espn.go.com&g=22222&g0=mlb&g1=story&n=1&f=00001&c=1.25&x=2129&m=2129&y=7615&o=1920&w=673&j=30&R=1&W=0&I=0&E=64&e=15&r=https%3A%2F%2Fwww.google.com%2F&t=p2IhuD3_JpF85OHk81UD8CwP20c&V=66&tz=420&sn=6&eS=79ff&_")
			.resources(http("request_92")
			.get(uri18 + "?tm=80145&pid=643&sid=2787525&wId=174&wRV=288799&rId=217f273f081de27884810e70f782f16a&eT=3&idx=0&pvId=217f273f081de27884810e70f782f16a&org=0&pad=3&pVis=1&eIdx=0&ab=0&wl=0")
			.headers(headers_1)))
		.pause(1)
		.exec(http("request_93")
			.get(uri20 + "")
			.headers(headers_47))
		.pause(12)
		.exec(http("request_94")
			.get("/ping?h=espn.go.com&p=%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&u=CgrkLrDuuYnKD5SMKM&d=espn.go.com&g=22222&g0=mlb&g1=story&n=1&f=00001&c=1.5&x=2303&m=2308&y=7615&o=1920&w=673&j=30&R=1&W=0&I=0&E=79&e=15&r=https%3A%2F%2Fwww.google.com%2F&t=p2IhuD3_JpF85OHk81UD8CwP20c&V=66&tz=420&sn=7&eS=7f7f&_"))
		.pause(11)
		.exec(http("request_95")
			.get(uri20 + "")
			.headers(headers_47)
			.resources(http("request_96")
			.get("http://" + uri09 + "/i/teamlogos/mlb/sprites/teams-mlb-25.png")))
		.pause(2)
		.exec(http("request_97")
			.get("/ping?h=espn.go.com&p=%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&u=CgrkLrDuuYnKD5SMKM&d=espn.go.com&g=22222&g0=mlb&g1=story&n=1&f=00001&c=1.75&x=109&m=2405&y=7615&o=1920&w=673&j=30&R=1&W=0&I=0&E=94&e=15&r=https%3A%2F%2Fwww.google.com%2F&KK=494::66::8jMruBk-acxDJNQte7mB-jCcMcnJ::*%5B%40id%3D%27global-nav%27%5D%2Ful%5B1%5D%2Fli%5B3%5D%2Fa%5B1%5D::c::http%3A%2F%2Fespn.go.com%2Fmlb%2F&t=p2IhuD3_JpF85OHk81UD8CwP20c&V=66&tz=420&sn=8&eS=5ff0&eM=001e&eD=0001&_")
			.resources(http("request_98")
			.get(uri04 + "?h=espn.go.com&p=%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&u=CgrkLrDuuYnKD5SMKM&d=espn.go.com&g=22222&g0=mlb&g1=story&n=1&f=00001&c=1.75&x=109&m=2405&y=7461&o=1920&w=673&j=45&R=0&W=0&I=1&E=94&e=0&r=https%3A%2F%2Fwww.google.com%2F&t=p2IhuD3_JpF85OHk81UD8CwP20c&V=66&tz=420&sn=9&_"),
            http("request_99")
			.get(uri10 + "/mlb/?xhr=1&render=true&device=desktop")
			.headers(headers_47),
            http("request_100")
			.get(uri10 + "/mlb/story/_/page/playoffs15_octoberphotos/31-mlb-most-memorable-postseason-moments?render=true&partial=article&xhr=1&device=desktop")
			.headers(headers_47),
            http("request_101")
			.get(uri25 + "/redesign/0.274.8/css/index.css")
			.headers(headers_47),
            http("request_102")
			.get(uri25 + "/redesign/0.274.8/css/index.css")
			.headers(headers_26),
            http("request_103")
			.get("http://" + uri12 + "/cgi-bin/m?ci=us-903969h&cg=0&cc=1&si=http%3A//espn.go.com/mlb/&rp=https%3A//www.google.com&ts=compact&rnd=1444855637953"),
            http("request_104")
			.get(uri25 + "/redesign/0.274.8/assets/img/icons/icon-espn-e-@2x.png"),
            http("request_105")
			.get(uri25 + "/i/columnists/schoenfield_david_m.jpg"),
            http("request_106")
			.get(uri21 + "?a=5872111&t=WebTransaction/Ajax/Uri/mlb/&qt=0&ap=0&be=352&dc=0&fe=409&c=1"),
            http("request_107")
			.get(uri15 + "?img=%2Fphoto%2F2015%2F1014%2Fr16904_1296x518_5%2D2.jpg&w=768&h=307&scale=crop&location=origin"),
            http("request_108")
			.get(uri04 + "?h=espn.go.com&p=%2Fmlb%2Findex&u=CgrkLrDuuYnKD5SMKM&d=espn.go.com&g=22222&g0=mlb&g1=index&n=1&f=00001&c=0&x=0&m=0&y=33604&o=1920&w=673&j=45&R=1&W=0&I=0&E=0&e=0&v=%2Fmlb%2Fstory%2F_%2Fid%2F13838249%2Fsan-francisco-giants-earn-mlb-best-coach-players-stadium-marks-ultimate-standings&vp=1&K=494::66::8jMruBk-acxDJNQte7mB-jCcMcnJ::*%5B%40id%3D%27global-nav%27%5D%2Ful%5B1%5D%2Fli%5B3%5D%2Fa%5B1%5D::c::http%3A%2F%2Fespn.go.com%2Fmlb%2F&t=CWMTe2C3MlMlxjI7SHR8irCiOkuY&V=66&z=t%3Dp2IhuD3_JpF85OHk81UD8CwP20c%26E%3D94%26x%3D0%26c%3D1.76%26y%3D33604%26w%3D673&i=MLB%20-%20Major%20League%20Baseball%20Teams%2C%20Scores%2C%20Stats%2C%20News%2C%20Standings%2C%20Rumors%20-%20ESPN&tz=420&sn=1&eS=0001&_"),
            http("request_109")
			.get("http://" + uri26 + "/b/ss/wdgespcom,wdgespge/1/JS-1.2.4/s23313007840149?AQB=1&ndh=1&t=14%2F9%2F2015%2013%3A47%3A17%203%20420&aid=29DED79B05149450-60000175800096A7&ce=ISO-8859-1&ns=espn&cdp=3&pageName=espn%3Amlb%3Aindex&g=espn.go.com%2Fmlb%2Findex&r=https%3A%2F%2Fwww.google.com&cc=USD&ch=espn%3Amlb&server=espn.go.com&events=event3&c1=espn&h1=espn%3Amlb%3Aindex&c2=D%3DSWID&c4=index&c5=espn%3Amlb%3Aindex&c6=New&v7=unknown%3Aunknown%3Aanonymous%3Ainsider-no%3Apremium-no&v9=en&c11=insider-no%3Apremium-no&v11=index%3Aespn%3Amlb&v13=espn%3Amlb%3Aindex&c17=en&v19=baseball&c21=unknown&v21=mlb&c22=unknown&v23=unknown&c24=First%20Visit&c25=baseball&c26=mlb&c29=anonymous&c30=premium-no&c38=Desktop&v38=Desktop&v70=has%20favorites%3Ano-fantasy%3Ano-notifications%3Ano-docking%3Ano-autostart%3Ano&s=1920x1080&c=24&j=1.8.5&v=Y&k=Y&bw=1920&bh=673&p=Adobe%20Acrobat%20NPAPI%20Plug-in%2C%20Version%2011.0.06%3BDefault%20Browser%20Helper%3BGoogle%20Earth%20Plug-in%3BGoogle%20Talk%20Plugin%3BGoogle%20Talk%20Plugin%20Video%20Renderer%3BJava%20Applet%20Plug-in%3BLogMeIn%20Plugin%201.0.0.730%3BQuickTime%20Plug-in%207.7.3%3BSharePoint%20Browser%20Plug-in%3BShockwave%20Flash%3BSilverlight%20Plug-In%3BWebEx64%20General%20Plugin%20Container%3Bzoom.us%20launcher%20plugin%3B&AQE=1"),
            http("request_110")
			.get(uri25 + "/combiner/i?img=/i/teamlogos/nba/500/ny.png&w=100&h=100&transparent=true"),
            http("request_111")
			.get(uri13 + "?img=/i/teamlogos/nba/500/scoreboard/bos.png&h=20&w=20"),
            http("request_112")
			.get(uri25 + "/combiner/i?img=/i/teamlogos/mlb/500/nyy.png&w=100&h=100&transparent=true"),
            http("request_113")
			.get(uri13 + "?img=/i/teamlogos/nba/500/scoreboard/ny.png&h=20&w=20"),
            http("request_114")
			.get(uri11 + "/b?c1=2&c2=3000005&ns__t=1444855638688&ns_c=UTF-8&c8=MLB%20-%20Major%20League%20Baseball%20Teams%2C%20Scores%2C%20Stats%2C%20News%2C%20Standings%2C%20Rumors%20-%20ESPN&c7=http%3A%2F%2Fespn.go.com%2Fmlb%2F&c9=https%3A%2F%2Fwww.google.com"),
            http("request_115")
			.get(uri25 + "/combiner/i?img=/i/teamlogos/nfl/500/nyj.png&w=100&h=100&transparent=true"),
            http("request_116")
			.get(uri25 + "/combiner/i?img=/i/teamlogos/nfl/500/nyg.png&w=100&h=100&transparent=true"),
            http("request_117")
			.get(uri13 + "?img=/i/teamlogos/nfl/500/scoreboard/nyg.png&h=20&w=20"),
            http("request_118")
			.get(uri13 + "?img=/i/teamlogos/nfl/500/scoreboard/phi.png&h=20&w=20"),
            http("request_119")
			.get(uri05 + "?img=%2Fphoto%2F2015%2F1005%2Fmlb_now_1296x729.png&w=210&h=118&scale=crop&cquality=90&location=origin"),
            http("request_120")
			.get(uri23 + "?img=%2Fmedia%2Fmotion%2F2015%2F1014%2Fdm_151014_Stark_predictions%2Fdm_151014_Stark_predictions.jpg&w=210&h=118&scale=crop&cquality=90&location=origin"),
            http("request_121")
			.get(uri23 + "?img=%2Fphoto%2F2015%2F1012%2F2015_mlb_world_series_1296x729.png&w=278"),
            http("request_122")
			.get(uri13 + "?img=/i/teamlogos/nfl/500/scoreboard/nyj.png&h=20&w=20"),
            http("request_123")
			.get(uri13 + "?img=/i/teamlogos/nfl/500/scoreboard/wsh.png&h=20&w=20"),
            http("request_124")
			.get(uri25 + "/combiner/i?img=/i/teamlogos/mlb/500/nym.png&w=100&h=100&transparent=true"),
            http("request_125")
			.get(uri25 + "/i/columnists/edes_gordon_m.jpg"),
            http("request_126")
			.get(uri25 + "/combiner/i?img=%2Fphoto%2F2015%2F1005%2Fr14297_1296x518_5%2D2.jpg&w=768&h=307&scale=crop&location=origin")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}