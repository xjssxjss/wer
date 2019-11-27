package com.wer.common;

import com.wer.entity.test.VisaInfo;
import com.wer.entity.test.VisaVisit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 常量配置
 */
public class GlobalConstant {
    private static final Logger logger = LoggerFactory.getLogger(GlobalConstant.class);

    public static Map<String,Object> visaDataMap = null;

    String visaChannel = "";
    static {
        logger.info("放入visaDataMap数据 begin");
        visaDataMap = new HashMap<>();

        String usaVisaChannel = "美利坚合众国（华盛顿）</br>\n" +
                "（THE UNITED STATES OF AMERICA）</br>\n" +
                "\n" +
                "申请美国非移民签证可分为两种情况：</br>\n" +
                "● 签证续签</br>\n" +
                "● 预约面谈</br>\n" +
                "\n" +
                "\n" +
                "● 预约面谈</br>\n" +
                "如果不符合签证续签要求的申请人，则需要到美领馆接受面谈并留取十指指纹，具体要求如下： </br>\n" +
                "一、10人以上（含10人）且出访目的除研究交流或商务会议外的因公出访团组（尤其是赴美的演出类团组）申请签证，请出访单位在面谈前2周将邀请函、详细行程及整个团组申请人的名单和护照号码（具体格式参见平台公共资料）以邮件形式发送至我处邮箱shfaousa@hotmail.com 。</br>\n" +
                "二、申办单位到外办护照签证处前台受理窗口预约美国签证面谈时间，并于面谈时间至少提前五个工作日至外办递交如下签证材料： </br>\n" +
                "1、申请相关签证类型所需的表格：</br>\n" +
                "（1）B、I类签证：DS160签证信息确认表。</br>\n" +
                "（2）J类签证：DS160签证信息确认表及DS-2019表复印件、SEVIS（I-901）缴费收据（证明申请人已缴纳SEVIS费用）。 </br>\n" +
                "（3）F、M类签证：DS160签证证信息确认表及I-20表复印件、SEVIS（I-901）缴费收据（证明申请人已缴纳SEVIS费用）。</br>\n" +
                "（4）H/O/P/Q类签证：DS160签证信息确认表及I-797表复印件。 </br>\n" +
                "（5）L类签证：DS160签证信息确认表、I-797表和I129表复印件。</br>\n" +
                "2、6个月内的2英寸正方形（51mm*51mm）白色背景的彩色正面照1张，照片不得佩戴眼镜（申请人出示医疗证明的特殊情况除外），不要配戴彩色隐形眼镜（美瞳）。</br>\n" +
                "3、护照复印件一份。</br>\n" +
                "4、简历。</br>\n" +
                "5、邀请信复印件一份。</br>\n" +
                "以上4、5项由外办前台接案审核后退回申办单位，由申请人自行带入美领馆面试。</br>\n" +
                "三、外办前台受理窗口收取合格的签证材料后，为每个申请人发放一张《美国面谈通知单》，上面有申请人面谈的具体编号和时间，请按指定时间准时到美领馆接受面谈并留取指纹。</br>\n" +
                "四、根据外办安排的面谈时间，请申请人带好《面谈通知单》及本人相关签证材料进馆面谈。 面谈时本人必须自行带入的材料如下： </br>\n" +
                "1、有效期超出预定停留期至少6个月的有效因公护照。</br>\n" +
                "2、所有良好出国记录的因公、因私护照。</br>\n" +
                "3、6个月内的2英寸正方形（51mm*51mm）白色背景的彩色正面照1张，照片不得佩戴眼镜（申请人出示医疗证明的特殊情况除外），不要配戴彩色隐形眼镜（美瞳）。</br>\n" +
                "4、DS160签证信息确认表。</br>\n" +
                "5、护照复印件一份。</br>\n" +
                "6、邀请信。</br>\n" +
                "7、简历。</br>\n" +
                "8、详细行程安排。</br>\n" +
                "9、除申请B类签证外，其它各类签证还需携带的申请材料： </br>\n" +
                "（1）F、M类签证：</br>\n" +
                "A、美国学校和项目提供的I-20表原件。</br>\n" +
                "B、SEVIS（I-901）缴费收据（证明申请人已缴纳SEVIS费用）。</br>\n" +
                "C、赴美研究/学习计划及相关的详细信息，其中包括美国学校的导师或/或系主任的姓名及电子邮箱地址。</br>\n" +
                "D、一份详细介绍以往学术和专业经验的简历，其中包括投过稿的出版物完整清单。</br>\n" +
                "E、录取通知书。</br>\n" +
                "F、导师的个人简介、简历或打印网页（针对所在美国教育机构已为其指定导师的研究生）。</br>\n" +
                "（2）J类签证：</br>\n" +
                "A、SEVIS（I-901）缴费收据（证明申请人已缴纳SEVIS费用）。除非申请人参加的J类签证项目是由美国政府资助（项目代码以“G”开头）。</br>\n" +
                "B、美国项目主办方提供的DS-2019表原件。</br>\n" +
                "C、赴美研究/学习计划及相关的详细信息，其中包括美国学校的导师或/或系主任的姓名及电子邮箱地址。</br>\n" +
                "D、一份详细介绍以往学术和专业经验的简历，其中包括投过稿的出版物完整清单。</br>\n" +
                "E、录取通知书。</br>\n" +
                "F、导师的个人简介、简历或打印网页（针对所在美国教育机构已为其指定导师的研究生）。</br>\n" +
                "（3）L类签证：</br>\n" +
                "A、I-797表原件以及I-129表原件。</br>\n" +
                "B、办理多次入境签证，需支付120美元的签证费（可用人民币支付）。 </br>\n" +
                "C、办理L1blanket签证的还需支付500美元费用（可用人民币支付）。</br>\n" +
                "（4）C类签证：</br>\n" +
                "A、途经美国前往第三国，无论停留多久，均需办妥过境签证：</br>\n" +
                "B、第三国签证或邀请信（英文）。</br>\n" +
                "C、机票订单。</br>\n" +
                "（5）I类签证：记者证</br>\n" +
                "（6）其他各签证种类的签证要求可参见美国签证申请官方网站：www.ustraveldocs.com。</br>\n" +
                "10、证明申请人同中国有牢固约束力的文件，如： </br>\n" +
                "（1）财产收入类材料：如单位出具的收入证明、银行出具的存款证明、股票凭证、房产证。 </br>\n" +
                "（2）职业和社会地位类材料：如工作证、名片、单位出具的在职证明等。 </br>\n" +
                "（3）学习经历类材料：如个人履历、毕业证书、学位证书、语言等级证书、论文和专著等。 </br>\n" +
                "（4）家庭关系类材料：如结婚证书、全家照片、配偶及孩子情况等。</br>\n" +
                "以上所带材料视个人情况而定。</br>\n" +
                "五、领馆可能要求补充的其它材料。</br>\n" +
                "六、面谈后取签证所需时间：3～5个工作日。</br>\n" +
                "\n" +
                "● 签证续签</br>\n" +
                "一、申请人如满足以下条件，符合免面谈要求的，可通过外办申请签证续签：</br>\n" +
                "1、持有B、C-1、D、C1/D、F、J、M、O类美国签证，且留取过十指指纹的申请人，在上述种类签证有效或失效12个月之内，再次申请同类签证时可免面谈。</br>\n" +
                "2、续签H-1、H-4、L、P-1或P-2类签证，原签证必须有效或失效12个月内，申请多次入境的L类签证不能续签，签证续签只接受申请一次入境的L签证。</br>\n" +
                "3、对注明“已接受过行政审理（Clearance Received）”的B类签证，签证上的行政审理日期必须在过去的12个月内。</br>\n" +
                "4、F-1类签证申请人需要继续在已入读的美国学校或机构学习。对原签证上注明“已接受过行政审理（Clearance Received）”的F-1类签证，签证上的行政审理日期必须在过去的12个月内。</br>\n" +
                "5、J-1类签证申请人需要继续参加以前的交流项目。对原签证注明“已接受过行政审理（Clearance Received）”的J-1类签证，签证上的行政审理日期必须在过去的12个月内。</br>\n" +
                "6、申请I类、P-3类或H-2B类签证，则无法申请续签签证。</br>\n" +
                "7、如果包含有效或到期美国签证的护照遗失，则无法申请免面谈续签签证。</br>\n" +
                "8、14岁以下或79岁以上的申请人，并且没有美国签证拒签史。</br>\n" +
                "9、申请人必须本人在中国。</br>\n" +
                "二 签证所需材料： </br>\n" +
                "1、B类签证：</br>\n" +
                "（1）有效期超出预定停留期至少6个月的有效护照。</br>\n" +
                "（2）以前赴美签证的护照，包括因私护照和已失效的护照。</br>\n" +
                "（3）6个月内的2英寸正方形（51mm*51mm）白色背景的彩色正面照1张，照片不得佩戴眼镜（申请人出示医疗证明的特殊情况除外），不要配戴彩色隐形眼镜（美瞳）。</br>\n" +
                "（4）DS160申请表确认页。</br>\n" +
                "（5）简历</br>\n" +
                "（6）邀请信。</br>\n" +
                "（7）详细行程表。</br>\n" +
                "（8）领馆可能要求面谈或补充其它材料。</br>\n" +
                "2、F、M类签证：</br>\n" +
                "（1）有效期超出预定停留期至少6个月的有效护照。</br>\n" +
                "（2）以前赴美签证的护照，包括因私护照和已失效的护照。</br>\n" +
                "（3）6个月内的2英寸正方形（51mm*51mm）白色背景的彩色正面照1张，照片不得佩戴眼镜（申请人出示医疗证明的特殊情况除外），不要配戴彩色隐形眼镜（美瞳）。</br>\n" +
                "（4）DS160申请表确认页。</br>\n" +
                "（5）简历。</br>\n" +
                "（6）美国学校和项目提供的I-20表复印件。</br>\n" +
                "（7）SEVIS（I-901）缴费收据（证明申请人已缴纳SEVIS费用）。</br>\n" +
                "（8）赴美研究/学习计划及相关的详细信息，其中包括美国学校的导师或/或系主任的姓名及电子邮箱地址。</br>\n" +
                "（9）一份详细介绍以往学术和专业经验的简历，其中包括投过稿的出版物完整清单。</br>\n" +
                "（10）美国教育机构所教授课程的官方成绩单。</br>\n" +
                "（11）导师的个人简介、简历或打印网页（针对所在美国教育机构已为其指定导师的研究生）。</br>\n" +
                "（12）领馆可能要求面谈或补充其它材料。</br>\n" +
                "3、J类签证：</br>\n" +
                "（1）有效期超出预定停留期至少6个月的有效护照。</br>\n" +
                "（2）以前赴美签证的护照，包括因私护照和已失效的护照。</br>\n" +
                "（3）6个月内的2英寸正方形（51mm*51mm）白色背景的彩色正面照1张，照片不得佩戴眼镜（申请人出示医疗证明的特殊情况除外），不要配戴彩色隐形眼镜（美瞳）。</br>\n" +
                "（4）DS160申请表确认页。</br>\n" +
                "（5）简历。</br>\n" +
                "（6）SEVIS（I-901）缴费收据（证明申请人已缴纳SEVIS费用）。除非申请人参加的J类签证项目是由美国政府资助（项目代码以“G”开头）。</br>\n" +
                "（7）美国项目主办方提供的DS-2019表复印件。</br>\n" +
                "（8）赴美研究/学习计划及相关的详细信息，其中包括美国学校的导师或/或系主任的姓名及电子邮箱地址。</br>\n" +
                "（9）一份详细介绍以往学术和专业经验的简历，其中包括投过稿的出版物完整清单。</br>\n" +
                "（10）美国教育机构所教授课程的官方成绩单。</br>\n" +
                "（11）导师的个人简介、简历或打印网页（针对所在美国教育机构已为其指定导师的研究生）。</br>\n" +
                "（12）领馆可能要求面谈或补充其它材料。</br>\n" +
                "4、H/L/O类签证：</br>\n" +
                "（1）有效期超出预定停留期至少6个月的有效护照。</br>\n" +
                "（2）以前赴美签证的护照，包括因私护照和已失效的护照。</br>\n" +
                "（3）6个月内的2英寸正方形（51mm*51mm）白色背景的彩色正面照1张，照片不得佩戴眼镜（申请人出示医疗证明的特殊情况除外），不要配戴彩色隐形眼镜（美瞳）。</br>\n" +
                "（4）DS160申请表确认页。</br>\n" +
                "（5）简历。</br>\n" +
                "（6）I-797、I-129表复印件。</br>\n" +
                "（7）领馆可能要求面谈或补充其它材料。</br>\n" +
                "5、C类签证：</br>\n" +
                "（1）途经美国前往第三国，无论停留多久，均需办妥过境签证。</br>\n" +
                "（2）有效期超出预定停留期至少6个月的有效护照。</br>\n" +
                "（3）以前赴美签证的护照，包括因私护照和已失效的护照。</br>\n" +
                "（4）6个月内的2英寸正方形（51mm*51mm）白色背景的彩色正面照1张，照片不得佩戴眼镜（申请人出示医疗证明的特殊情况除外），不要配戴彩色隐形眼镜（美瞳）。</br>\n" +
                "（5）DS160申请表确认页。</br>\n" +
                "（6）第三国签证或邀请信(英文)。</br>\n" +
                "（7）简历。</br>\n" +
                "（8）机票订单。</br>\n" +
                "（9）领馆可能要求面谈或补充其它材料。</br>\n" +
                "三、签证续签所需时间：5～7个工作日。</br>\n" +
                "\n" +
                "友情提示：</br>\n" +
                "一、外办在收到申请单位递交材料后，会统一为团组登陆美国签证申请官方网站：http://www.ustraveldocs.com付费及预约送签和面谈时间，申请人请勿自行预约或支付签证费。</br>\n" +
                "二、简历</br>\n" +
                "参见：http://www.shfao.gov.cn/上海外事网站“签证表格下载”处的“美国简历标准样张”。提供近五年内出访国家的英文记录，只需国家和年份，包括因公护照和因私护照上所有的出访记录。</br>\n" +
                "三、行政处理</br>\n" +
                "根据申请人的背景和经历，美领馆会对部分申请人做额外的行政处理，签证官会在面谈时告知，如需进一步行政处理，所需时间一般为一个月，但也有部分申请个案将视具体情况而定。建议申请人准备好材料并安排好时间。在此期间，申请人的护照暂留美领馆，行政处理一旦完成，领馆会签发签证并将护照退还上海外办。</br>\n" +
                "1、查询</br>\n" +
                "签证申请如已到工作日但签证还未签出，申请人可上ceac.state.gov查询签证情况。</br>\n" +
                "2、借照</br>\n" +
                "在签证行政处理期间，如申请人需要使用因公护照，由申请单位出具中（抬头为上海外办）、英文（抬头为美领馆）借照函，中文函交外办、英文函由外办交美领馆借照。</br>\n" +
                "3、申请人在面谈或签证申请结束后确认自己被行政处理的，请及时通知申办单位并告知外办。</br>\n" +
                "4、即使因公旧护照已经到期，但包含一个有效的美国签证，只有使用包含有有效签证的旧因公护照和有效的因公护照才可以出访。两本护照上的名字和其他个人信息必须一致，而且必须是同一国家签发的同一类型护照。凭旧护照上的10年多次B1、B2或B1/B2签证与新护照一同出访的，须在出访前进行EVUS更新（填签证号及新护照号码）。</br>\n" +
                "四、相关网址：</br>\n" +
                "美国驻沪总领事馆网址：http://shanghai.usembassy-china.org.cn </br>\n" +
                "查询签证进度及DS-160签证表填写网址：http://ceac.state.gov/</br>\n" +
                "美国签证官方网站网址：http://www.ustraveldocs.com</br>\n" +
                "签证更新电子系统（EVUS）网站：www.evus.gov</br>\n" +
                "\n" +
                "\n" +
                "● 因公转因私签证</br>\n" +
                "一、有效因公护照上有美国签证此次持因私护照申办美国，且符合上述签证续签条件的，需通过外办办理签证，具体要求如下：</br>\n" +
                "1、因公转因私签证目的仅指赴美人员已经按照人员管理权限所在组织人事部门同意的探亲、旅游等因私目的。</br>\n" +
                "2、填写《因私赴外签证事项表》，请加盖公章，并提供单位同意申请人持因私护照出访美国的情况说明，加盖公章。</br>\n" +
                "3、签证所需材料：</br>\n" +
                "（1）有效期超出预定停留期至少6个月的因私护照。</br>\n" +
                "（2）以前赴美签证的因公护照。</br>\n" +
                "（3）6个月内的2英寸正方形（51mm*51mm）白色背景的彩色正面照1张，照片不得佩戴眼镜（申请人出示医疗证明的特殊情况除外），不要配戴彩色隐形眼镜（美瞳）。</br>\n" +
                "（4）DS160申请表确认页。</br>\n" +
                "（5）详细行程安排。</br>\n" +
                "（6）视个人情况递交的材料。</br>\n" +
                "（7）领馆可能要求补充的其它材料。</br>\n" +
                "二、所需时间：5～7个工作日。</br>\n";
        VisaVisit v = new VisaVisit("美国","美利坚合众国","U.S.A","THE UNITED STATES OF AMERICA","MeiGuo","MG","北美洲","国外",usaVisaChannel,"总领事馆","上海","","","","","","否","","2019-08-09","范晔","","");

        String japanChannel = "日本国（东京）</br>\n" +
                "（JAPAN）</br>\n" +
                "\n" +
                "一、所需时间：固定5个工作日。</br>\n" +
                "二、短期签证所需申请材料：</br>\n" +
                "1、一表（正反面复印，中文填写）、一照。</br>\n" +
                "2、邀请方提供（均需原件）：</br>\n" +
                "(1) 邀请理由书</br>\n" +
                "邀请人一栏中必须写明公司或团体名称、地址、职务、姓名、联系电话并盖章（如外国人没有印章的，必须签名）。公司团体邀请的须盖代表印章或公司印章（个人印章不可）。大学教授或准教授邀请的可以盖个人印章（理由书须明确体现准教授以上职务）；</br>\n" +
                "邀请方为“海外産業人材育成協会”的技术研修生须提供接受研修保证书（研修生受入れ並びに身元保証書）。</br>\n" +
                "(2) 滞在预定表，内容包括：A.进入日本至出日本的具体日期；B.每日具体活动内容、担当姓名、部门、电话；C.住宿宾馆名称、地址及电话（须与签证申请表上相关项目一致）。</br>\n" +
                "3、合资企业，需提供：</br>\n" +
                "(1) 企业成立“批准证书”（外资委批准证书）复印件；</br>\n" +
                "(2) 企业“营业执照副本”复印件；</br>\n" +
                "4、如赴日培训团组，另需提供合同复印件或详细培训计划书。</br>\n" +
                "5、赴日参加国际会议，如在会议上作发言，需附发言材料及有关会议介绍。</br>\n" +
                "6、材料有效期：所有材料均为三个月（自制作之日起算）。</br>\n" +
                "注：凡以持外交护照或公务护照任出访团团长的团组可免日方材料，签证表等中方材料仍要提供，另须由出访团组制作一份“详细访日安排计划”（用单位信笺纸，中文打印盖章，内容可参照日方的滞在日程表样式）。</br>\n" +
                "三、由日本商工俱乐部会员企业邀请，所需申请材料：</br>\n" +
                "1、一表（中文填写）、一照。</br>\n" +
                "2、短期商用单次签证申请理由书：日资企业商工俱乐部会员企业制作(需盖章)</br>\n" +
                "3、日资商工俱乐部会员证明材料：日资企业商工俱乐部等会员名簿中该公司一栏复印件(上海日本商工俱乐部会员公司需提交由日本商工俱乐部网站下载的会员名簿)</br>\n" +
                "4、滞在预定表</br>\n" +
                "5、合资企业，需提供：</br>\n" +
                "(1) 企业成立“批准证书”（外资委批准证书）复印件；</br>\n" +
                "(2) 企业“营业执照副本”复印件；</br>\n" +
                "四、持“在留资格认定证明书”，所需申请材料：</br>\n" +
                "1、一表（中文填写）、一照。</br>\n" +
                "2、提供“在留资格认定书”一正一副(正件订在护照的签证页背面上，副件订在签证表前)。(“在留资格认定证明书”须按原来大小复印；复印件须留出上部5公分、左部3公分的间距)</br>\n" +
                "3、研修引受保证书。</br>\n" +
                "4、劳务团组须提供：户口复印件、派遣单位在职证明、如被派遣人员户籍所在地与其工作单位所在地不一致，还需提供其工作单位所在地的居住证复印件。</br>\n" +
                "5、如领馆有特殊要求需补充合同书、结婚公证书及护照复印件。</br>\n" +
                "五、赴日探亲、另需提供配偶的在职证明、合同书、结婚证书及护照复印件。</br>\n" +
                "六、商务签证一年多次申办要求：</br>\n" +
                "1、申请人有访日经历；</br>\n" +
                "2、填写多次申请理由书（可至签证处领取），单位盖章；</br>\n" +
                "3、其它要求同短期。</br>\n" +
                "七、领馆可能要求补充的其它材料。</br>\n" +
                "八、短期签证领馆一般发给三个月有效，停留十五天或九十天的一次入境签证。</br>\n" +
                "九、持联程机票，72小时内不出机场，可免办签证。出机场停留的，须办过境签证，交联程机票订单、停留日程表（可参照日方滞在日程表格式）。如借过境之便入境办事，应事先办妥入境签证，否则将被阻止出机场。</br>\n" +
                "十、有关要求：</br>\n" +
                "1、申请签证材料必须严格按要求提供，不合格材料将不予受理或不予发签证。</br>\n" +
                "2、材料整理顺序：</br>\n" +
                "(1) 签证表(按照会人员顺序)</br>\n" +
                "(2) 中方资料：合资企业“营业执照副本”、“批准书”，劳务团组的“户口本”、派遣单位“在职证明”等</br>\n" +
                "(3) 日方资料：入国理由、查证申请人名簿、滞在日程表、“培训计划”或“合同”等。最后请用“回形针”将材料夹好。</br>\n" +
                "3、凡由中央部、委在沪单位申办在外省市工作人员的签证时，需出具一份情况说明（送领馆用）。</br>\n" +
                "\n" +
                "注1：</br>\n" +
                "日本领馆通知：作为东日本特大地震灾后复兴支援活动的一环，前往日本宫城县，福岛县，岩手县其中任何一个地区访问者，如果满足以下条件，即可免除签证费。</br>\n" +
                "一、免除签证费所需条件：</br>\n" +
                "1、实施对象：</br>\n" +
                "(1) 申请短期签证以外的签证时在实施对象地区居住，工作或者留学的人员</br>\n" +
                "(2) 申请短期签证时前往实施对象地区访问的人员（备注：实施对象地区指的是宫城县，福岛县，岩手县）</br>\n" +
                "2、实施对象的签证</br>\n" +
                "   自2011年11月15日起至2016年3月31日申请的签证。</br>\n" +
                "二、所需材料：</br>\n" +
                "    提交签证申请所需材料外，还需提供下列材料：</br>\n" +
                "1、申请短期签证以外的签证时可以证明居住，工作或留学所在地为实施对象地区的材料。</br>\n" +
                "2、申请短期签证时</br>\n" +
                "(1) 滞在预定表</br>\n" +
                "(2) 可以证明前往实施对象地区访问的材料住宿预约单，机票预约单，船票预约单，火车预约单，各活动的入场券或预约单，在实施对象地区召开会议的邀请函等任一材料。</br>\n" +
                "*申请时请在“上海申请因公出国护照签证事项表”中注明。　</br>\n" +
                "\n" +
                "注2：</br>\n" +
                "自2007年11月20日起，申请进入日本国境的外国人在接受入境审查时，必须提供指纹和面部照片，并接受入境审查官的面试。此规定亦适用于已经在日本居留的外国人再入境。若拒绝提供指纹或面部照片，将不被允许入境，并被命令离开日本，该命令下达后将不可更改。免留指纹和面部照片的人员包括：1、特别永住者；2、未满16周岁的人员；3、进行“外交”或“公务”在留资格活动的人员（指获得“外交”“公务”类别签证者。其他人员即使持外交、公务护照，如不属于日本中央政府邀请、未获得“外交”、“公务”类别签证者，也要提供指纹和面部照片）。4、由日本行政机构首长邀请的来日人员。5、按法务省政令，符合第（3）项或第（4）项的准身份入境人员。</br>\n";
        VisaVisit v1 = new VisaVisit("日本","日本国","JAPAN","JAPAN","RiBen","RB","亚洲","国外",japanChannel,"总领事馆","上海","","","","","","否","","2017-10-26","赵捷毅","","");

        String ruiShiChannel = "瑞士联邦（伯尔尼）</br>\n" +
                "（SWITZERLAND）</br>\n" +
                "\n" +
                "一、《中华人民共和国政府和瑞士联邦委员会关于互免持外交护照人员签证的协定》于2016年1月29日生效。</br>\n" +
                "    协定规定：中华人民共和国持有效的外交护照的公民，在任意连续的180日内，在瑞士入境、出境并停留不超过90日，免办签证，由颁照单位开具出境证明。</br>\n" +
                "二、持公务护照和公务普通护照者须办理签证：</br>\n" +
                "（一）所需时间：5个工作日以上</br>\n" +
                "（二）短期签证：</br>\n" +
                "1、瑞士指纹采集信息表（可在平台“公共资料查看”中下载）。</br>\n" +
                "2、一表二照（表格可网上下载，网址：www.eda.admin.cn/shanghai；可打印成A4纸，单面、双面均可，表中住址一栏加填邮编，签证表签名须与护照签名一致）</br>\n" +
                "3、邀请函原件（函中须有被邀请人出生年月、单位、职务及详细的日程安排等内容）。同时申办几个申根国家签证，须提供所有国家邀请函原件和详细日程（邀请函出访时间须依次衔接），并在签证表上填写所有申请签证国家的起止时间，派遣单位的出差证明也要写明所有国名和每个国家停留起止时间。</br>\n" +
                "4、持公务护照申请，只需提供邀请函原件、签证表、护照复印件即可。</br>\n" +
                "5、持公务普通护照申请，分两种情况：</br>\n" +
                "（1）若邀请方系国际组织（领馆认可37家国际组织，名单详见“外事专管员平台公共资料”栏），则需提供邀请函原件、保单、护照复印件即可。</br>\n" +
                "（2）若邀请方系非国际组织，则需提供：</br>\n" +
                "1）邀请函原件\n" +
                "2）派出单位机构代码或营业执照</br>\n" +
                "3）派遣单位的出差证明（单位信笺纸英文打印，请严格参照护照签证处的出差证明样式，食宿、交通、医疗保险一一写明，如邀请方承担，邀请函中注明，加盖公章并有负责人签名)</br>\n" +
                "4）保单（保额不低于3万欧元，姓名须用拼音，出访时须随身携带此保单原件。</br>\n" +
                "5）护照复印件\n" +
                "* 注：以上材料每人一套，依次排放，夹在各自护照中，原件放在带队人材料中。</br>\n" +
                "（三）长期签证（90天以上）：需事先取得瑞士国内的相关批复，领馆凭批复贴签。所需材料：一份D类签证表、两张照片（一贴一别）、批复、护照复印件。</br>\n" +
                "（四）领馆可能要求补充的其它材料。</br>\n" +
                "三、去列支敦王国，申请签证要求同瑞士。</br>\n" +
                "四、领馆周五不受理签证申请</br>\n" +
                "五、过境瑞士，不出机场，24小时之内免办签证。</br>\n" +
                "\n" +
                "* 中国公民赴波兰等六个申根成员国注意事项[2009年9月转自外交部网站]</br>\n" +
                "为避免持中国外交、公务护照人员在比利时、德国、法国等申根国家转机前往波兰、匈牙利、斯洛伐克、斯洛文尼亚、立陶宛及马耳他六个免签申根成员国受阻，外交部领事司谨提醒：根据双边协议，持中国外交、公务护照人员可免签赴波兰、匈牙利、斯洛伐克、斯洛文尼亚、立陶宛及马耳他六个申根成员国，但须乘直航前往上述六国。免签人员如过境其他申根国家前往上述六国，须提前办妥有关申根国家的签证。</br>";
        VisaVisit v2 = new VisaVisit("瑞士","瑞士联邦","SWITZERLAND","SWITZERLAND","RuiShi","RS","欧洲","国外",ruiShiChannel,"总领事馆","上海","","","","","","否","","2016-12-16","曹嘉","仙霞路319号远东国际广场A幢22楼","一～五  09:00～11:00");

        String v3Channel = "德意志联邦共和国（柏林）</br>\n" +
                "（GERMANY）</br>\n" +
                "\n" +
                "    《中华人民共和国和欧洲联盟关于互免持外交护照人员短期停留签证的协定》于2016年3月3日起临时适用。</br>\n" +
                "    协定规定，中国持有效外交护照的公民，在德国旅行且在申根区域每180日停留最长不超过90日，免办签证，由颁照单位开具出境证明。</br>\n" +
                "\n" +
                "持公务、公务普通护照者须申办签证：</br>\n" +
                "一、申根签证（短期90天以内），所需时间：5个工作日</br>\n" +
                "1、一表、两照。</br>\n" +
                "在线填写、申请人本人签名的电子表格，登录http://www.china.diplo.de/或https://videx.diplo.de网站，按照网上填表说明要求填写后，连同条形码一并打印下来，条形码为黑白两色有效，如中间有灰色或其它颜色掺杂则视为无效、照片尺寸为4.5cm×3.5cm，白色背景二张相同的6个月内近照，所附的一张照片不要剪小。</br>\n" +
                "2、发自德国的邀请函原件。</br>\n" +
                "函末须打印邀请人姓名、职务，尔后邀请人再签全名，函中须有出访人员姓名、出生日期、护照号码（新做护照可提供身份证号）、停留天数、行程、费用说明（如邀请方承担，须分别写明来回机票、在德食宿、健康保险的费用担保；如中方承担，须出具经济担保证明，单位信笺纸英文打印，被邀请人名单如另附纸，也须邀请人用单位信笺纸打印姓名、职务，尔后签名。</br>\n" +
                "如同时出访多个申根国家，申请德国签证，须在德国逗留天数多于其他申根国家，或各国逗留天数相同时，首入申根国为德国；须提供所有国家邀请函原件，邀请函出访时间须依次衔接，派遣单位的出差证明、经济担保证明也要写明所有国名和每个国家停留起止时间和总停留天数。</br>\n" +
                "3、如邀请单位是中资公司，须提供注册登记证明复印件。</br>\n" +
                "4、派遣单位的出差证明和经济担保（单位信笺纸英文打印，请严格参照护照签证处的出差证明样式，同时增加出访人员的出生日期、护照号码等内容）。</br>\n" +
                "5、详细访德日程安排（团组带队人单位信笺纸按领馆提供的格式英文打印一份，此格式可至上海外办网站查询）</br>\n" +
                "6、提供在所有申根国家有效、覆盖整个申请逗留期的医疗保险证明。</br>\n" +
                "    中英文境外旅行医疗保单复印件，姓名须用拼音，并在复印件上注明“已与原件核对无误”。德领馆建议：如出访人员购买逗留天数增加至少16天的保险，则签发的签证有效期会相应延长。在签证表中的出入境时间即按保险时间填写，而停留天数不变。</br>\n" +
                "    根据申根国家法律规定，提供旅行医疗保险是签发申根签证的基本前提，所提供的医疗保险保额不得低于3万欧元，医疗保险必须包括由于生病可能送返回国的费用及急救和紧急住院费用。如提供境外保险，须提供保单条款且要求同国内。出国时请随身携带保单原件。</br>\n" +
                "**德国签证表填写注意事项，表格填写不当将致审核过程延长：</br>\n" +
                "（1）第一栏：姓氏填写一律以护照为准，如：多音字或双姓等；（2）第二栏：出生姓氏不填。（3）第三栏：名填写须同护照拼写一致，如：双名间不得有空格或其他符号。（4）出生地：同护照，只填省份。（5）旅行证件种类：公务普通护照 填写06；公务护照 填写05；外交护照 填写04。（6）签发机关：填写须同护照：MINISTRY OF FOREIGN AFFAIRS，不得填中文。（7）签发地：填写须同护照：SHANGHAI，不得填中文。（8）目的申根国：只填写德国。（9）第三十二栏 ：须填德方邀请人 “在德国”的相关信息，不得填写中国地址。（10）递交的申请表姓名、条形码的姓名与护照姓名须完全一致。</br>\n" +
                "\n" +
                "二、申请赴德工作、实习或学术访问（长期90天以上）：</br>\n" +
                "1、两份长期申请表（A4纸张打印，德文填写并亲笔签名）与附加声明表（可从签证处索取或www.china.diplo.de下载）；三照（相同的近期白底护照照片）。</br>\n" +
                "2、德国联邦劳动署（ZAV）出具的预先核准信原件（如有）</br>\n" +
                "3、学术访问须提供德国高校或科研机构的德文邀请函原件。（注明学术活动的内容、居留时间和居留期间的费用承担）。</br>\n" +
                "4、派遣单位的德文出差证明并复印一份（单位信笺纸打印，请严格参照护照签证处的出差证明样式，如中方提供经济担保，可把经济担保和出差证明内容打印在同一份证明上）。</br>\n" +
                "5、有效护照（护照有效期6个月以上）与护照资料页复印件二份。</br>\n" +
                "6、自入境之日起至少90天有效的在德医疗保险证明原件并复印二份。</br>\n" +
                "7、申请人提供以下原件材料并附两份复印件：户口薄原件与户口簿内个人信息页的德文译文、德文书写完整的个人简历、高校学历证书原件与外语语言水平证明附德文译文、德文的工作/派遣/实习合同（其中须含收入与社会保险方面的内容）。</br>\n" +
                "8、奖学金获得者需提供德方或中方获奖学金证明原件并复印二份。</br>\n" +
                "9、申请人本人需周四13:00～14:00至德领馆签证处留取指纹，由我处受理时预约。</br>\n" +
                "* 以上材料按顺序，每人一正两副，加上三张照片夹入两份申请表中。</br>\n" +
                "** 申请德国工作或实习签证时，德国驻外使、领馆需要德国联邦劳动署外劳职业介绍中心的协助。递交签证申请材料前，德国雇主可预先申请德国联邦劳动署（ZAV）出具的预先核准信原件，这将加快签证申请程序。申请者可通过网站（www.arbeitsagentur.de）查询更多信息。</br>\n" +
                "\n" +
                "三、一年或多年多次往返申根签证（须在任务批件中注明）：</br>\n" +
                "1、申请人曾去过申根国家并预计多次前往申根国家进行商务访问，需在签证申请表中注明：出境日期距离境日期应有一年的时间，计划停留时间一栏填写90天，入境次数选择多次入境。</br>\n" +
                "2、来自德国的邀请函应写明申请人在未来的一年中多次入境的必要性并附日程。</br>\n" +
                "3、申请签证时，申请人只需提供覆盖首次行程的旅行医疗保险。</br>\n" +
                "四、18岁以下未成年者赴申根国家须提供父母同意公证书和出生公证书，公证书须经外办认证。单亲家庭还须提供离异证明书和在公证书中说明签名者是该未成年者的监护人及父母双方同意公证书。</br>\n" +
                "五、领馆可能要求补充的其它材料。</br>\n" +
                "\n" +
                "* 中国公民赴波兰等六个申根成员国注意事项[2009年9月转自外交部网站]</br>\n" +
                "为避免持中国外交、公务护照人员在比利时、德国、法国等申根国家转机前往波兰、匈牙利、斯洛伐克、斯洛文尼亚、立陶宛及马耳他六个免签申根成员国受阻，外交部领事司谨提醒：根据双边协议，持中国外交、公务护照人员可免签赴波兰、匈牙利、斯洛伐克、斯洛文尼亚、立陶宛及马耳他六个申根成员国，但须乘直航前往上述六国。免签人员如过境其他申根国家前往上述六国，须提前办妥有关申根国家的签证。</br>\n" +
                "** 经与德领馆签证处协商，持外交、公务护照前往波兰、匈牙利、斯洛伐克、斯洛文尼亚、立陶宛、马耳他六个互免签证的申根国家，必须过境德国时，须申办德国签证，且申请天数应包含上述前往国家的停留总天数。</br>";
        VisaVisit v3 = new VisaVisit("德国","德意志联邦共和国","GERMANY","GERMANY","DeGuo","DG","欧洲","国外",v3Channel,"总领事馆","上海","","","","","","是","","2018-07-20","赵捷毅","上海市铜仁路299号东海广场8楼","周一14:30～15:30；周二、三、四8:30～9:30，14:30～15:30；周五8:30～9:30(每半小时为一时段)");

        String v4Channel = "俄罗斯联邦（莫斯科）</br>\n" +
                "（RUSSIA）</br>\n" +
                "\n" +
                "一、持外交、公务护照者入境、过境停留不超过30天，免办签证，由发照单位开具出境证明。如需停留超过30天，应提前办妥签证手续。</br>\n" +
                "\n" +
                "二、持公务普通护照者需申办签证：</br>\n" +
                "1、所需时间：5个工作日。</br>\n" +
                "2、每人一份俄文登记表（邀请单位名称须用俄文写明，落款和日期不用填写）。</br>\n" +
                "3、邀请函（除工作、学习签证外不必须提供原件。邀请函须俄本土发出，邀请方单位信笺纸打印，须包括邀请单位全称、地址和联系方式，负责人亲笔签名并打印姓名和职务，出访人员姓名、性别、出生日期、国籍、护照号码、职务，出访目的、申请签证类型：商务/学术交流/公务访问/会议等、入境次数：一次/二次/多次、具体出访时间与停留天数：如8月1日到8月5日之间停留5天、费用说明等信息。上述所列要素缺一不可，否则领馆将不予受理）。</br>\n" +
                "4、除政府机关外，邀请函中须注明企业税务登记号VHH（如不注明则须提供税务登记证复印件）。</br>\n" +
                "5、出访人员护照复印件和身份证复印件（复印在同一张A4纸的靠右处，护照复在上面、身份证复在下面，如出访人员所持身份证非上海领区，另须在身份证旁复印上海领区签发的居住证）。</br>\n" +
                "6、一表（请登录俄外交部领事司网站http://visa.kdmid.ru，填写电子版签证申请表并打印，申请地点必须选“驻上海领事馆”，因公不得填选“签证中心”）、一照（照片为35mm*45mm，必须贴在方框内）。</br>\n" +
                "* 俄邀请函参考格式及税务登记证样式可至我办网站“签证表格下载”获取。</br>\n" +
                "* 送签材料请按上述序号依次排放整理，每人一套夹在护照中，如团组共用邀请函原件，则团长放原件，团组其他成员放复印件。</br>\n" +
                "三、申办工作签证另须提供：\n" +
                "1、俄移民局签发的邀请函、劳动许可原件及复印件。</br>\n" +
                "2、俄单位营业执照复印件。\n" +
                "3、出访人员体检证书原件（包括健康证明、艾滋病检查）并复印一份。</br>\n" +
                "四、申办过境签证另须提供：</br>\n" +
                "1、第三国有效签证及复印件。</br>\n" +
                "2、全程机票订单。</br>\n" +
                "* 经俄罗斯机场转机前往白俄罗斯，必须申办俄过境签证；转机前往除白俄罗斯以外的其他国家，24小时内不离开机场过境区域，可不办过境签证，但须与航空公司确认；国际列车车组人员、船员和机组人员执行公务时凭有效旅行证件和人员名单免办签证。</br>\n" +
                "五、领馆可能要求补充的其它材料。</br>\n" +
                "六、领馆每周一、三、五上午受理和签发因公签证。</br>";
        VisaVisit v4 = new VisaVisit("俄罗斯","俄罗斯联邦","RUSSIA","RUSSIAN FEDERATION","ELuoSi","ELS","欧洲","国外",v4Channel,"总领事馆","上海","","","","DS","","否","","2018-03-05","曹洋","","");

        String v5Channel = "墨西哥合众国（墨西哥城）</br>\n" +
                "（MEXICO）</br>\n" +
                "\n" +
                "一、持外交、公务护照（停留三个月以内）者免办签证，由发照单位开具出境证明。</br>\n" +
                "二、持公务普通护照者需办签证：</br>\n" +
                "1、所需时间：10个工作日（每月第一个和最后三个工作日不受理新申请）。</br>\n" +
                "2、团组名单表，空白A4纸中英文对照打印，填写人员姓名、护照号码、出生日期、单位名称、职务，见以下格式：</br>\n" +
                "\n" +
                "访问人员名单</br>\n" +
                "\n" +
                "姓名\t护照号码\t生日（月、日、年）\t工作单位\t职务</br>\n" +
                "张三\tP.xxxxxxx\txxxx\t\txxx</br>\n" +
                "李四\tP.xxxxxxx\txxxx\t\txxxx</br>\n" +
                "\n" +
                "NAME\tPASSPORT NUMBER\tDATE OF BIRTH（MON-DAY-YEAR）\tUNITS\tDUTY</br>\n" +
                "ZHANG SAN\tP.xxxxxxx\t\txxxx\txxx</br>\n" +
                "LI SI\tP.xxxxxxx\t\txxxx\txxxx</br>\n" +
                "\n" +
                "3、一表一照（表格2页须正反面复印在一张纸上，不得漏填，不得涂改，特别是第V项“与申请表一同递交的材料”，表格签名须签在指定方框内并注明拼音，签名须与护照页签名一致；照片须为32mm×26mm～39mm×31mm不戴眼镜的正面清晰白底彩照）。</br>\n" +
                "4、护照以及护照上其他国家签证页复印件（用A4纸复印），非电子护照需另外复印签名页。</br>\n" +
                "5、邀请函（墨方公司抬头纸打印，包含墨方公司名、地址、电话等基本信息，被邀请人的姓名、具体的访墨目的、停留时间；商务或贸易活动的介绍、行程安排等，并注明访问费用的承担方）。</br>\n" +
                "6、派遣单位的出差证明（单位信笺纸英语或西班牙语打印，上面必须包含中方公司的名字、地址、电话等基本信息。信中须注明访问已由公司批准，访问费用由哪方承担。同时必须注明申请人的姓名；职务；具体的访墨目的；商务或贸易活动的介绍；行程安排；停留时间等。如果是技术人员，还需要说明申请者在墨西哥期间的工资是由中方还是墨西哥方面提供。出差证明需单位负责人手签、盖章）。</br>\n" +
                "7、申请人所属公司／单位的营业执照复印件。</br>\n" +
                "8、科研或技术人员需提供最高学历证明，复印件。</br>\n" +
                "9、派遣单位出具每人一份的英文在职及税后月收入证明（该证明用单位信笺纸打印，须包含员工的具体入职工作日期(注明年月日）、担任职务，并以表格形式列出最近六个月各月的税后月收入数额，相关负责人亲笔签名并打印签字人姓名、职务，单位盖章。工作年限应满一年否则将被要求前往领馆面试并补充其他材料，税后月收入须至少超过RMB4700，原则上收入信息应更新至办签当月）。</br>\n" +
                "\n" +
                "注：上述2至9项送签材料请按顺序每人单独整理一份夹在各自护照中，原件放在带队人材料中。</br>\n" +
                "三、领馆可能要求补充的其它材料。</br>\n" +
                "\n" +
                "* 凡持有美国有效签证的外国人以旅游、商务或过境为目的前往墨西哥，可免办墨西哥签证，但在墨期间不得涉及任何工资收入方面的事宜。如首站赴墨，还须至发照单位开具出境证明。</br>\n" +
                "* 中国公民持有加拿大、美国、日本、英国或申根国家多次有效签证，非过境签证，非二次签证，且入境停留目的为非劳务访问（例如商务、非劳务的技术、科研工作），可免签入境墨西哥，停留期小于180天。</br>\n";
        VisaVisit v5 = new VisaVisit("墨西哥","墨西哥合众国","MEXICO","MEXICO","MoXiGe","MXG","北美洲","国外",v5Channel,"总领事馆","上海","","","","","","否","","2019-07-26","曹洋","","");

        String v6Channel = "澳大利亚联邦（堪培拉）</br>\n" +
                "（AUSTRALIA）</br>\n" +
                "\n" +
                "    澳大利亚驻上海总领事馆从2010年2月1日开始发一年多次无贴纸电子签证.</br>\n" +
                "出访人员凭本人护照及外办签发的《出境证明》可出境。澳领馆提供给申请人的电子签证批准通知书出访时请随身携带。</br>\n" +
                "    持澳大利亚多次有效电子签证再次因公出访人员，办理相关出国手续并出具单位情况说明与申请人电子签证查询（VEVO: online.immi.gov.au/）结果打印页到外办开出境证明。</br>\n" +
                "    600类访客签证</br>\n" +
                "一、所需时间：5个工作日。</br> \n" +
                "二、网上申请：按公共资料中的申请指南所列步骤操作。信息填写完整并按照材料清单上传附件后将申请发送至我处账户shfaoau@vip.163.com，并按照预定递交时间提前至窗口送办。窗口送办时须提供团组表一份及邀请函复印件一份，其余签证材料不需在窗口递交。</br>\n" +
                "三、网上上传材料（按材料清单中的命名方式）</br>\n" +
                "1、护照资料页彩色扫描件；</br>\n" +
                "2、身份证扫描件；</br>\n" +
                "3、邀请函扫描件；</br>\n" +
                "4、派遣函及组织机构代码/营业执照扫描件；</br>\n" +
                "5、凡去澳大利亚医疗机构团组需体检，澳大利亚驻上海总领事馆发体检通知与护照复印件，体检中心列表如下：</br>\n" +
                "\n" +
                "    瑞新国际医疗中心   </br>\n" +
                "      上海商城西峰315室</br>\n" +
                "      上海南京西路1376号 邮编：200040</br>\n" +
                "         周一至周五8:30 - 19:00， 周六周日8:30 - 17:00  （请提前预约）</br>\n" +
                "      电话:(86 21) 6279 8129 传真: (86 21) 6279 8329</br>\n" +
                "     上海快验保门诊部   </br>\n" +
                "     上海市浦东新区浦东南路256号华夏银行大厦102室， 邮编：200120</br>\n" +
                "        周一至周五8:00 ? 17:00 (请提前预约)，周六，周日: 8:00 ?12:00</br>\n" +
                "        电话: (86 21) 5887 8260 传真: (86 21) 5887 8261</br>\n" +
                "        电子邮箱: imedical@163.com</br>\n" +
                "    闸北区中心医院 移民体检中心   </br>\n" +
                "     上海闸北区中华新路619号 辅诊楼6楼, 邮编:200070</br>\n" +
                "       时间 上午8:00到12:00, 下午1:30到5:30</br>\n" +
                "       周一到周五全天, 周六上午</br>\n" +
                "     电话: (86 21) 3653 3651 传真:(86 21) 3635 6612</br>\n" +
                "    上海怡东门诊部   </br>\n" +
                "     上海市淮海中路1325号爱美高大厦304-309室，邮编：200070</br>\n" +
                "       周一至周五8:00 ? 17:00，周六8:00-12:00 (请提前预约)</br>\n" +
                "       电话: (86 21) 6473 0055 传真: (86 21) (86 21) 6403 2012</br>\n" +
                "       电子邮箱: sheaton@eatonclinic.com</br>\n" +
                "    上海国际旅行卫生保健中心    </br>\n" +
                "    上海市长宁区金浜路15号3号楼2楼，邮编：200335</br>\n" +
                "      周一至周五上午8:30 - 12:00, 下午1:00 - 4:00 (请提前预约)</br>\n" +
                "      电话: (86 21) 6268 3085 传真: (86 21) 6269 7117</br>\n" +
                "      电子邮箱: shanghaiithc@163.com；</br>\n" +
                "\n" +
                "6、“凡18岁以下（含18岁）”赴澳大利亚访问，交流等，需提供本人出生证、父母双方签字同意的1229表、户口本、父母双方身份证及父母双方的结婚证或离婚证；</br>\n" +
                "7、70岁以上者赴澳签证需要体检；</br>\n" +
                "8、持海员证人员申请签证时要提供申请人的因私护照及海员证；</br>\n" +
                "9、领馆可能要求补充的其它材料。</br>\n" +
                "\n" +
                "临时活动类签证（400类别/408类别等）</br>\n" +
                "一、一律网上申请（具体操作步骤参照公共资料中相关指南）</br>\n" +
                "二、所需时间：5个工作日以上（视不同签证子类而定，如需补充材料的，审理时间重置）</br>\n" +
                "三、网上上传所需材料：</br>\n" +
                "1、护照资料页（彩色）；</br>\n" +
                "2、身份证；</br>\n" +
                "3、邀请函（须提供详细日程）；</br>\n" +
                "4、派遣公司的营业执照 或 派遣单位的组织机构代码证；</br>\n" +
                "5、派遣单位的出差证明（单位信笺纸英文打印，可参照护照签证处的出差证明样式，出访单位自行出具，不可由组团单位或上级单位代出）；</br>\n" +
                "6、其他澳大利亚移民与边境保卫部网站所要求上传的材料。</br>\n" +
                "四、澳大利亚移民与边境保卫部网站视各申请人的具体情况要求上传材料，请预留出充足的签证材料准备及网上审理时间。</br>\n" +
                "五、递交申请后请密切关注网上申请时所填的联系邮箱，澳大利亚使、领馆任何关于此团组签证申请的信息都会直接以电子邮件方式进行通知。如澳大利亚使、领馆要求补充材料的，请及时与我处相关工作人员联系。</br>\n" +
                "六、签证审理完成后签证结果将直接发送到网上申请时所填的联系邮箱，请仔细核对各申请人的准签信息后将准签信打印并与我处相关工作人员联系出境证明开具事宜。</br>\n" +
                "\n" +
                "771类过境签证</br>\n" +
                "网上申请，类别选择771，按要求填写相应信息。</br>\n" +
                "所需时间：5个工作日。</br>\n" +
                "\n" +
                "\n" +
                "电子签证有效期内更换护照需提供：</br>\n" +
                "所需时间：5个工作日</br>\n" +
                "1）填写完整的929表（见附件或公共资料）并签字.2）英文情况说明，须写明：a. 此次出访目的；b. 持有的有效签证签发日期、有效期限及准签号（grant number）；c. 申请人新、旧护照号码及护照更换原因。文末请注明：I agree to appoint Mr. Fan YE from Shanghai Foreign Affairs Office as the authorized recipient. 由申请人本人签名并由派出单位盖章.3）申请人新、旧护照资料页彩色扫描件. 4）申请人身份证复印件.</br>\n" +
                "同时将上述材料扫描成一个PDF文件，发送至外办邮箱 SHFAOAU@VIP.163.COM</br>\n" +
                "\n";
        VisaVisit v6 = new VisaVisit("澳大利亚","澳大利亚联邦","AUSTRALIA","AUSTRALIA","AoDaLiYa","ADLY","大洋洲","国外",v6Channel,"总领事馆","上海","","","","","","否","","2019-11-02","范晔","","");
        visaDataMap.put("美国",v);    //美国
        visaDataMap.put("日本",v1); //日本
        visaDataMap.put("瑞士",v2); //瑞士
        visaDataMap.put("德国",v3); //德国
        visaDataMap.put("俄罗斯",v4); //俄罗斯
        visaDataMap.put("墨西哥",v5); //墨西哥
        visaDataMap.put("澳大利亚",v6); //澳大利亚

        logger.info("放入visaDataMap数据 end");
    }
    //项目前缀
    public static String KEY_PREFIX = "wer";
    //redis key分割符
    public static String KEY_SPLIT_CHAR = ":";

    //文件常用后缀
    public static final String PROP_SUFFIX = ".properties";

    //微信企业号发送的消息类型
    public static final String WX_MSG_TYPE = "MsgType";

    //所有配置文件名称,多个中间以,逗号进行分隔
    public static final String BASE_FILE_NAMES = "wer,webservice";

    //数据访问成功提示
    public static final String SUCCESS_MESSAGE = "数据访问成功!!";

    public static final String ERROR_MESSAGE = "数据访问失败!!";

    public static final String ALREADTY_AUTHORIZE = "已经认证通过，请勿重新认证";

    //附件类型
    public static final String FILE_TYPE = "FILE_TYPE";

    /**
     * clickKey
     */
    public static final String MSG_QUERY_CLICK = "msg_query_click";

    public static final String VISA_QUERY_CLICK = "visa_query_click";

    public static final String JOIN_QR_CODE_CLICK = "join_qr_code_click";

    public static final String INTER_URIS_STR = "/wer/messageController/queryMessageByMsgId,";

    /**
     * 跳转界面
     */
    //护照签证要求
    public static final String VISA_CLAIM = "visa/visa_claim";
    //
    public static final String GRID = "apec/grid";
    //APEC卡办理
    public static final String APEC_SELECT = "apec/apec_select";
    //APEC卡在线申办
    public static final String APEC_ON_LINE = "apec/apec_on_line";
    //公告详情界面
    public static final String MSG_DETAIL = "msg/msg_detail";
    //认证界面
    public static final String AUTH_INFO = "auth/authorize";

    //同意协议页面
    public static final String AGREEMENT = "auth/agreement";
    //认证成功页面
    public static final String AGREEMENT_SUCCESS = "auth/success";
    //认证失败页面
    public static final String AGREEMENT_ERROR = "auth/error";

    public static Set<String> userList = new HashSet<>();

    //***********************************redis keys begin*******************************//
    //key失效时间
    public static final int REDIS_KEY_EXPIRE_SECONDS = 60*90;

    //企业微信全局access_token
    public static final String REDIS_KEY_ACCESS_TOKEN = "access_token";

    //企业微信用户通讯录的access_token
    public static final String REDIS_KEY_ADDRESS_BOOK_ACCESS_TOKEN = "address_book_access_token";

    //JS_API_TICKET
    public static final String REDIS_KEY_JS_API_TICKET = "js_api_ticket";
    //************************************redis keys end******************************//
}
