/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : localhost:3306
 Source Schema         : web

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 16/05/2018 15:52:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission` int(2) NOT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin', 1);
INSERT INTO `admin` VALUES ('2', 'guanli', 'guanli', 1);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `cid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '手机数码');
INSERT INTO `category` VALUES ('2', '电脑办公');
INSERT INTO `category` VALUES ('3', '时尚潮服');
INSERT INTO `category` VALUES ('4', '家具家电');
INSERT INTO `category` VALUES ('5', '鞋靴箱包');
INSERT INTO `category` VALUES ('6', '图书音像');
INSERT INTO `category` VALUES ('7', '食品生鲜');
INSERT INTO `category` VALUES ('8', '汽车用品');

-- ----------------------------
-- Table structure for collections
-- ----------------------------
DROP TABLE IF EXISTS `collections`;
CREATE TABLE `collections`  (
  `coid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`coid`) USING BTREE,
  INDEX `fk_coid_uid`(`uid`) USING BTREE,
  INDEX `fk_coid_pid`(`pid`) USING BTREE,
  CONSTRAINT `fk_coid_pid` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_coid_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of collections
-- ----------------------------
INSERT INTO `collections` VALUES ('1446DD5BAE844A45BFAFFCEB96763EE4', '373eb242933b4f5ca3bd43503c34668b', '2');
INSERT INTO `collections` VALUES ('26FE7C71246145268FCDA2AC867F5AE3', '373eb242933b4f5ca3bd43503c34668b', '65');
INSERT INTO `collections` VALUES ('3D0E6E9D696142C0B278908D9628EF1A', '373eb242933b4f5ca3bd43503c34668b', '81');
INSERT INTO `collections` VALUES ('6BDF8BEA74EC42639B80D4850359DA66', '373eb242933b4f5ca3bd43503c34668b', '121');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `itemid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `count` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `oid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`itemid`) USING BTREE,
  INDEX `fk_0001`(`pid`) USING BTREE,
  INDEX `fk_0002`(`oid`) USING BTREE,
  CONSTRAINT `fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_0002` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('45741DD4D19945558D71347EC1BF1B6A', 1, 2.2, '149', 'B27773D4752B40439F4417771B38C608');
INSERT INTO `orderitem` VALUES ('8AF1B21DE52545E4B059A748A1272010', 1, 28, '144', 'B27773D4752B40439F4417771B38C608');
INSERT INTO `orderitem` VALUES ('A6D177FC259E446BBFCC3EE4CEA8DEC4', 1, 2299, '50', '95C1F74CECC744CAB23402D35348C1CC');
INSERT INTO `orderitem` VALUES ('B12E692518274EB895C076B13582CE39', 1, 135, '130', 'B27773D4752B40439F4417771B38C608');
INSERT INTO `orderitem` VALUES ('BB01D38863244FC9AB3A7A4CF15DDC3F', 1, 4899, '47', '77B5AF5E7E244B89AAD307918C43FEFD');
INSERT INTO `orderitem` VALUES ('C19C9C20D13C4735BC36E5C24E421BFF', 1, 278, '108', 'B780D643F6A6411F8165EB856FECF3A1');
INSERT INTO `orderitem` VALUES ('E178ACE324B54D2F95DD639A4D1BF1A2', 1, 128, '96', 'B780D643F6A6411F8165EB856FECF3A1');
INSERT INTO `orderitem` VALUES ('FE319FD6A81E490BB3EA482A7C4DBFA2', 1, 658, '70', '95C1F74CECC744CAB23402D35348C1CC');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `oid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ordertime` datetime(0) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('77B5AF5E7E244B89AAD307918C43FEFD', '2018-05-14 19:29:50', 4899, 4, '小张', '河西学院', '21312434233', '373eb242933b4f5ca3bd43503c34668b');
INSERT INTO `orders` VALUES ('95C1F74CECC744CAB23402D35348C1CC', '2018-05-16 15:36:18', 2957, 1, '王先生', '甘肃省', '231234123', '373eb242933b4f5ca3bd43503c34668b');
INSERT INTO `orders` VALUES ('B27773D4752B40439F4417771B38C608', '2018-05-16 15:37:42', 165.2, 0, '北京', '李先生', '2131231231', '373eb242933b4f5ca3bd43503c34668b');
INSERT INTO `orders` VALUES ('B780D643F6A6411F8165EB856FECF3A1', '2018-05-16 15:38:45', 406, 0, '上海', '朱女士', '231321312', '373eb242933b4f5ca3bd43503c34668b');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `shop_price` double DEFAULT NULL,
  `pimage` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pdate` date DEFAULT NULL,
  `is_hot` int(11) DEFAULT NULL,
  `pdesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pflag` int(11) DEFAULT NULL,
  `cid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `sfk_0001`(`cid`) USING BTREE,
  CONSTRAINT `sfk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '小米 4c 标准版', 1399, 1299, 'products/1/c_0001.jpg', '2018-05-03', 1, '小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待', 0, '1');
INSERT INTO `product` VALUES ('10', '华为 Ascend Mate7', 2699, 2599, 'products/1/c_0010.jpg', '2015-11-02', 0, '华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！', 0, '1');
INSERT INTO `product` VALUES ('100', '迷你小挎包男士单肩牛津布时尚小包包休闲运动单肩包斜挎包女', 78, 47, 'products/1/xiexue05.jpg', '2018-05-01', 0, '迷你小挎包男士单肩牛津布时尚小包包休闲运动单肩包斜挎包女', 0, '5');
INSERT INTO `product` VALUES ('101', 'Samayes2017夏新款女包真皮菱格链条手拿包宴会小方包流行单肩包', 467, 257, 'products/1/xiexue06.jpg', '2018-05-10', 0, 'Samayes2017夏新款女包真皮菱格链条手拿包宴会小方包流行单肩包', 0, '5');
INSERT INTO `product` VALUES ('102', '真皮女包大包2018春季新品单肩包斜跨手提白色四方托特包包牛皮包', 550, 416, 'products/1/xiexue07.jpg', '2018-05-07', 0, '真皮女包大包2018春季新品单肩包斜跨手提白色四方托特包包牛皮包', 0, '5');
INSERT INTO `product` VALUES ('103', '新款真皮手提包男横款韩版单肩斜挎包商务包牛皮公文包电脑包男包', 335, 225, 'products/1/xiexue08.jpg', '2018-05-14', 0, '新款真皮手提包男横款韩版单肩斜挎包商务包牛皮公文包电脑包男包', 0, '5');
INSERT INTO `product` VALUES ('104', '慢时光 复古疯马皮男士背包休闲头层牛皮双肩包女真皮旅行电脑包 ', 223, 189, 'products/1/xiexue09.jpg', '2018-05-05', 0, '慢时光 复古疯马皮男士背包休闲头层牛皮双肩包女真皮旅行电脑包 ', 0, '5');
INSERT INTO `product` VALUES ('105', 'JanSport双肩包正品纯色杰斯伯T501超级叛逆男女背包休闲双肩背包 ', 188, 158, 'products/1/xiexue10.jpg', '2018-05-08', 0, 'JanSport双肩包正品纯色杰斯伯T501超级叛逆男女背包休闲双肩背包 ', 0, '5');
INSERT INTO `product` VALUES ('106', '三叶草新款EQT BASK ADV 男女运动鞋AC7354 CQ2993 CQ2995 ', 588, 536, 'products/1/xiexue11.jpg', '2018-05-13', 0, '三叶草新款EQT BASK ADV 男女运动鞋AC7354 CQ2993 CQ2995 ', 0, '5');
INSERT INTO `product` VALUES ('107', '刀锋战士跑鞋减震跑步鞋健身鞋休闲旅游鞋夏季透气健身房运动鞋男 ', 268, 159, 'products/1/xiexue12.jpg', '2018-05-05', 0, '刀锋战士跑鞋减震跑步鞋健身鞋休闲旅游鞋夏季透气健身房运动鞋男 ', 0, '5');
INSERT INTO `product` VALUES ('108', '男士新款休闲鞋夏季男鞋子韩版潮流英伦百搭商务皮鞋男正装低帮鞋 ', 368, 278, 'products/1/xiexue13.jpg', '2018-05-12', 0, '男士新款休闲鞋夏季男鞋子韩版潮流英伦百搭商务皮鞋男正装低帮鞋 ', 0, '5');
INSERT INTO `product` VALUES ('109', '男鞋夏季鞋子男休闲鞋2018新款韩版潮流男士皮鞋英伦百搭板鞋潮鞋 ', 345, 268, 'products/1/xiexue14.jpg', '2018-05-09', 0, '男鞋夏季鞋子男休闲鞋2018新款韩版潮流男士皮鞋英伦百搭板鞋潮鞋 ', 0, '5');
INSERT INTO `product` VALUES ('11', 'vivo X5Pro', 2399, 2298, 'products/1/c_0014.jpg', '2015-11-02', 0, '移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术', 0, '1');
INSERT INTO `product` VALUES ('110', '欧美露趾粗跟凉鞋2017夏季新款中跟高跟鞋方木根一字扣凉鞋女绿色', 218, 158, 'products/1/xiexue15.jpg', '2018-05-11', 0, '欧美露趾粗跟凉鞋2017夏季新款中跟高跟鞋方木根一字扣凉鞋女绿色', 0, '5');
INSERT INTO `product` VALUES ('111', '2018新款夏季透气 真爆椰子鞋350v2女白色涂鸦侃爷运动休闲跑步男', 358, 289, 'products/1/xiexue16.jpg', '2018-05-13', 0, '2018新款夏季透气 真爆椰子鞋350v2女白色涂鸦侃爷运动休闲跑步男', 0, '5');
INSERT INTO `product` VALUES ('112', '2018新款海军风韩版帆布马苏叶一茜同款女包双肩包背包学生书包大', 89, 79, 'products/1/xiexue17.jpg', '2018-05-10', 0, '2018新款海军风韩版帆布马苏叶一茜同款女包双肩包背包学生书包大', 0, '5');
INSERT INTO `product` VALUES ('113', '双肩包女2018新款韩版学院风学生百搭时尚书包街头潮流牛津布背包', 252, 158, 'products/1/xiexue18.jpg', '2018-05-11', 0, '双肩包女2018新款韩版学院风学生百搭时尚书包街头潮流牛津布背包', 0, '5');
INSERT INTO `product` VALUES ('114', 'Nike 耐克官方 NIKE RUN SWIFT 女子跑步鞋 909006', 599, 489, 'products/1/xiexue19.jpg', '2018-05-12', 0, 'Nike 耐克官方 NIKE RUN SWIFT 女子跑步鞋 909006', 0, '5');
INSERT INTO `product` VALUES ('115', '高版本!2018新款麦昆小白鞋女真皮袁姗姗同款厚底百搭增高运动鞋', 388, 288, 'products/1/xiexue20.jpg', '2018-05-02', 0, '高版本!2018新款麦昆小白鞋女真皮袁姗姗同款厚底百搭增高运动鞋', 0, '5');
INSERT INTO `product` VALUES ('116', '文化苦旅 新版 ', 39.9, 35, 'products/1/shuji01.jpg', '2018-04-28', 0, '\r\n文化苦旅 新版\r\n文化导师余秋雨开山之作，新版出版近1年重掀文化热，深思中国历史之力作《文化之痛》全新收录！影响三代华人的文化价值观，值得全家人一读再读的经典之作。', 0, '6');
INSERT INTO `product` VALUES ('117', '城南旧事：一部触动千万读者灵魂的童年离骚 ', 28, 20.5, 'products/1/shuji02.jpg', '2018-04-20', 0, '城南旧事：一部触动千万读者灵魂的童年离骚 ', 0, '6');
INSERT INTO `product` VALUES ('118', '傲慢与偏见 英文原版小说', 39, 32, 'products/1/shuji03.png', '2018-04-28', 0, 'Pride and Prejudice简奥斯汀 英国文学经典\r\n奥斯汀代表作 经典名著 原版进口 ', 0, '6');
INSERT INTO `product` VALUES ('119', '嫌疑人X的献身', 19, 15.9, 'products/1/shuji04.jpg', '2018-04-19', 0, '★东野圭吾颠峰杰作 　　★★同时荣获直木奖、本格推理小说大奖、三大推理小说排行榜年度总冠军 　　“这本小说了不起”第1名 　　“本格推理小说Top 10”第1名 　　“周刊文艺推理小说Top 10”第1名） 　　★日本文学荣誉直木奖获奖作品 　　★他将骗局写到了极致。直木奖评语 　　 　 　　★横扫日本文坛，在拿奖拿到手软之余，还被票选为“年度受欢迎小说” ', 0, '6');
INSERT INTO `product` VALUES ('12', '努比亚（nubia）My 布拉格', 1899, 1799, 'products/1/c_0013.jpg', '2015-11-02', 0, '努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！', 0, '1');
INSERT INTO `product` VALUES ('120', '东野圭吾：解忧杂货店 ', 40.5, 32.5, 'products/1/shuji05.jpg', '2018-04-26', 0, '《白夜行》后东野圭吾备受欢迎作品：不是推理小说，却更扣人心弦。中国影响力图书年度读者推荐大奖，800万中国读者挚爱选择。', 0, '6');
INSERT INTO `product` VALUES ('121', ' Java核心技术 卷I：基础知识（原书第10版）', 119, 93.5, 'products/1/shuji06.jpg', '2018-04-29', 0, '全新第10版！Java领域极具影响力和价值的著作之一，与《Java编程思想》齐名，10余年全球畅销不衰，广受好评', 0, '6');
INSERT INTO `product` VALUES ('122', '深入理解Java虚拟机：JVM高级特性与最佳实践（第2版）', 79, 58.3, 'products/1/shuji07.jpg', '2018-04-20', 0, '超级畅销书全新升级，第1版两年内印刷近10次，Java图书领域公认的经典著作，繁体版台湾发行 ', 0, '6');
INSERT INTO `product` VALUES ('123', ' Python核心编程 第3版 ', 99, 78.6, 'products/1/shuji08.jpg', '2018-04-27', 0, '畅销经典的Python基础教程学习手册进阶图书 兼顾Python2和Python3 机器学习 数据处理 网络爬虫热门编程语言 Python开发人员的案头常备 ', 0, '6');
INSERT INTO `product` VALUES ('124', '梦想永远不会太晚——“济公”游本昌的智慧人生 ', 42, 29.9, 'products/1/shuji09.jpg', '2018-04-25', 0, '济公扮演者，游本昌首部传记 ★85版《济公》珍贵文字影像资料，幕后花絮，首次独家披露 ★老一辈表演艺术家毕其一生的舞台经验表演技巧独家传授 ★人民艺术家的六十年表演历程，几代电视迷的集体回忆', 0, '6');
INSERT INTO `product` VALUES ('125', ' 牛奶可乐经济学 ', 49.9, 32.9, 'products/1/shuji10.jpg', '2018-04-26', 0, '荣膺第四届“国家图书馆文津图书奖，通俗经济学鼻祖罗伯特?弗兰克经典力作，著名经济学家梁小民倾情推荐', 0, '6');
INSERT INTO `product` VALUES ('126', '梦的解析（软精装珍藏本）', 36, 27, 'products/1/shuji11.jpg', '2018-04-27', 0, '著名心理学家、翻译大家孙名之权威译本！改变人类历史的划时代经典著作！ ', 0, '6');
INSERT INTO `product` VALUES ('127', ' 几何原本', 58, 48, 'products/1/shuji12.jpg', '2018-04-18', 0, '建立空间秩序久远权威的逻辑推测语系', 0, '6');
INSERT INTO `product` VALUES ('128', '良品铺子手撕面包营养早餐食品全麦蛋糕小糕点点心零食批发整箱 ', 59, 29, 'products/1/shipin01.jpg', '2018-05-02', 0, '良品铺子手撕面包营养早餐食品全麦蛋糕小糕点点心零食批发整箱 ', 0, '7');
INSERT INTO `product` VALUES ('129', '猴头菇饼干5斤散装猴菇无糖精饼干整箱批发糖尿人零食早餐食品 ', 62, 46, 'products/1/shipin02.jpg', '2018-05-10', 0, '猴头菇饼干5斤散装猴菇无糖精饼干整箱批发糖尿人零食早餐食品 ', 0, '7');
INSERT INTO `product` VALUES ('13', '华为 麦芒4', 2599, 2499, 'products/1/c_0012.jpg', '2015-11-02', 0, '华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖', 0, '1');
INSERT INTO `product` VALUES ('130', '三只松鼠森林大礼包2476g 特产食品干果礼盒坚果组合12袋盒装一箱 ', 168, 135, 'products/1/shipin03.jpg', '2018-05-09', 0, '三只松鼠森林大礼包2476g 特产食品干果礼盒坚果组合12袋盒装一箱 ', 0, '7');
INSERT INTO `product` VALUES ('131', '思宏香酥脆灰枣252g*2袋新疆特产无核枣嘎嘣脆枣休闲零食品冬脆枣 ', 59, 42, 'products/1/shipin04.jpg', '2018-05-08', 0, '思宏香酥脆灰枣252g*2袋新疆特产无核枣嘎嘣脆枣休闲零食品冬脆枣 ', 0, '7');
INSERT INTO `product` VALUES ('132', '川香鸡柳400g鸡米花鸡排鸡块 油炸冷冻食品生鲜批发 ', 20, 15, 'products/1/shipin05.jpg', '2018-05-11', 0, '川香鸡柳400g鸡米花鸡排鸡块 油炸冷冻食品生鲜批发 ', 0, '7');
INSERT INTO `product` VALUES ('133', '冻鸡翅中翅1kg 新鲜冷冻速冻批发生鸡肉 生鲜冷冻鸡翅中 ', 315, 245, 'products/1/shipin07.jpg', '2018-05-08', 0, '冻鸡翅中翅1kg 新鲜冷冻速冻批发生鸡肉 生鲜冷冻鸡翅中 ', 0, '7');
INSERT INTO `product` VALUES ('134', '生鲜蔬菜 有机食品 西红柿 番茄 有机蔬菜天津 同城配送 即拍即摘 ', 20, 14, 'products/1/shipin06.jpg', '2018-05-12', 0, '生鲜蔬菜 有机食品 西红柿 番茄 有机蔬菜天津 同城配送 即拍即摘 ', 0, '7');
INSERT INTO `product` VALUES ('135', '澳洲新鲜生牛排套餐肉眼西冷沙朗战斧总厨原切黑胡椒牛扒8块装 ', 317, 279, 'products/1/shipin08.jpg', '2018-05-12', 0, '澳洲新鲜生牛排套餐肉眼西冷沙朗战斧总厨原切黑胡椒牛扒8块装 ', 0, '7');
INSERT INTO `product` VALUES ('136', '生鲜蔬菜 有机食品 土豆 马铃薯 有机蔬菜天津 同城配送 即拍即摘', 17, 12, 'products/1/shipin10.jpg', '2018-05-09', 0, '生鲜蔬菜 有机食品 土豆 马铃薯 有机蔬菜天津 同城配送 即拍即摘', 0, '7');
INSERT INTO `product` VALUES ('137', '长生食品甜青豆400g 新鲜蔬菜', 25, 18, 'products/1/shipin09.jpg', '2018-05-08', 0, '长生食品甜青豆400g 新鲜蔬菜', 0, '7');
INSERT INTO `product` VALUES ('138', '米多奇烤香馍片40包2kg整箱早餐食品烤馍馒头片饼干批发零食礼包 ', 44.8, 37.8, 'products/1/shipin11.jpg', '2018-05-14', 0, '米多奇烤香馍片40包2kg整箱早餐食品烤馍馒头片饼干批发零食礼包 ', 0, '7');
INSERT INTO `product` VALUES ('139', '友臣肉松饼2.5斤整箱早餐食品糕点面包批发散装60个零食小吃特产 ', 118, 79, 'products/1/shipin12.jpg', '2018-05-13', 0, '友臣肉松饼2.5斤整箱早餐食品糕点面包批发散装60个零食小吃特产 ', 0, '7');
INSERT INTO `product` VALUES ('14', 'vivo X5M', 1899, 1799, 'products/1/c_0011.jpg', '2015-11-02', 0, 'vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV', 0, '1');
INSERT INTO `product` VALUES ('140', '新疆特产 特级吐鲁番绿香妃葡萄干500g 超大粒免洗青提子干果包邮', 55, 37, 'products/1/shipin13.jpg', '2018-05-08', 0, '新疆特产 特级吐鲁番绿香妃葡萄干500g 超大粒免洗青提子干果包邮', 0, '7');
INSERT INTO `product` VALUES ('141', '海边人 香酥小黄鱼200g*2黄花鱼即食鱼干鱼仔青岛特产海鲜零食 ', 34, 28, 'products/1/shipin14.jpg', '2018-05-08', 0, '海边人 香酥小黄鱼200g*2黄花鱼即食鱼干鱼仔青岛特产海鲜零食 ', 0, '7');
INSERT INTO `product` VALUES ('142', '正宗祥禾红豆沙栗子玛 天津北京特产传统手工松软糕点心零食 ', 32.8, 25.8, 'products/1/shipin15.jpg', '2018-05-08', 0, '正宗祥禾红豆沙栗子玛 天津北京特产传统手工松软糕点心零食 ', 0, '7');
INSERT INTO `product` VALUES ('143', '猪肉脯1斤靖江特产肉干肉脯大礼包500g休闲食品零食小吃 ', 49.9, 35.9, 'products/1/shipin16.jpg', '2018-05-13', 0, '猪肉脯1斤靖江特产肉干肉脯大礼包500g休闲食品零食小吃 ', 0, '7');
INSERT INTO `product` VALUES ('144', '乖媳妇单爪山椒凤爪32g/43g*30包泡椒鸡爪重庆特产办公休闲零食品 ', 35, 28, 'products/1/shipin17.png', '2018-05-10', 0, '乖媳妇单爪山椒凤爪32g/43g*30包泡椒鸡爪重庆特产办公休闲零食品 ', 0, '7');
INSERT INTO `product` VALUES ('145', '进口厄瓜多尔白虾大虾鲜活超大四斤海鲜对虾水产冻虾鲜虾基围虾 ', 118, 88, 'products/1/shipin18.jpg', '2018-05-09', 0, '进口厄瓜多尔白虾大虾鲜活超大四斤海鲜对虾水产冻虾鲜虾基围虾 ', 0, '7');
INSERT INTO `product` VALUES ('146', '农巴老新鲜贝贝小南瓜5斤孕妇宝宝辅食迷你蔬菜栗面老南瓜板栗味 ', 57, 42, 'products/1/shipin19.jpg', '2018-05-03', 0, '农巴老新鲜贝贝小南瓜5斤孕妇宝宝辅食迷你蔬菜栗面老南瓜板栗味 ', 0, '7');
INSERT INTO `product` VALUES ('147', '冰糖心苹果水果10斤批发包邮 当季新鲜水果丑萍果红富士 整箱现发 ', 89, 69, 'products/1/shipin20.jpg', '2018-05-08', 0, '冰糖心苹果水果10斤批发包邮 当季新鲜水果丑萍果红富士 整箱现发 ', 0, '7');
INSERT INTO `product` VALUES ('148', '小台芒果新鲜包邮10斤整箱海南小芒果现摘现发广西水果小台农芒果 ', 75, 55, 'products/1/shipin21.jpg', '2018-05-07', 0, '小台芒果新鲜包邮10斤整箱海南小芒果现摘现发广西水果小台农芒果 ', 0, '7');
INSERT INTO `product` VALUES ('149', '中秋广式月饼莲蓉蛋黄豆沙五仁水果多口味散装团购100g满8个包邮 ', 6.8, 2.2, 'products/1/shipin22.jpg', '2018-05-13', 0, '中秋广式月饼莲蓉蛋黄豆沙五仁水果多口味散装团购100g满8个包邮 ', 0, '7');
INSERT INTO `product` VALUES ('15', 'Apple iPhone 6 (A1586)', 4399, 4288, 'products/1/c_0015.jpg', '2015-11-02', 1, 'Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！', 0, '1');
INSERT INTO `product` VALUES ('150', '重庆特产磁器口陈昌银小麻花 好吃不贵的零食小吃休闲食品成人款', 24, 18, 'products/1/shipin23.jpg', '2018-05-13', 0, '重庆特产磁器口陈昌银小麻花 好吃不贵的零食小吃休闲食品成人款', 0, '7');
INSERT INTO `product` VALUES ('16', '华为 HUAWEI Mate S 臻享版', 4200, 4087, 'products/1/c_0016.jpg', '2015-11-03', 0, '华为 HUAWEI Mate S 臻享版 手机 极昼金 移动联通双4G(高配)满星评价即返30元话费啦；买就送电源+清水套+创意手机支架；优雅弧屏，mate7升级版', 0, '1');
INSERT INTO `product` VALUES ('160', 'TITA汽车临时停车牌挪车电话号码牌创意移车牌隐藏式汽车装饰用品 ', 49, 39, 'products/1/qiche01.jpg', '2018-04-11', 0, 'TITA汽车临时停车牌挪车电话号码牌创意移车牌隐藏式汽车装饰用品 ', 0, '8');
INSERT INTO `product` VALUES ('161', '汽车头枕护颈枕靠腰颈枕汽车用品车内用品车载车枕头记忆棉一对 ', 58, 48, 'products/1/qiche02.jpg', '2018-04-28', 0, '汽车头枕护颈枕靠腰颈枕汽车用品车内用品车载车枕头记忆棉一对 ', 0, '8');
INSERT INTO `product` VALUES ('162', '汽车后备箱收纳箱储物箱车内杂物收纳盒车载置物用品多功能整理箱', 99, 69, 'products/1/qiche03.jpg', '2018-04-28', 0, '汽车后备箱收纳箱储物箱车内杂物收纳盒车载置物用品多功能整理箱', 0, '8');
INSERT INTO `product` VALUES ('163', '3M汽车清洁用品轮毂清洁剂铝合金钢圈除锈剂锈渍清洗喷剂去油污', 99, 79, 'products/1/qiche04.jpg', '2018-04-29', 0, '3M汽车清洁用品轮毂清洁剂铝合金钢圈除锈剂锈渍清洗喷剂去油污', 0, '8');
INSERT INTO `product` VALUES ('164', '漫威汽车摆件用品钢铁侠车载车内装饰品中控台高档男可爱个性创意 ', 123, 98, 'products/1/qiche05.jpg', '2018-05-01', 0, '漫威汽车摆件用品钢铁侠车载车内装饰品中控台高档男可爱个性创意 ', 0, '8');
INSERT INTO `product` VALUES ('165', '魅迪汽车挂钩车上用座椅背隐藏式车载手机磁性支架多功能车内用品 ', 59, 36, 'products/1/qiche06.jpg', '2018-04-29', 0, '魅迪汽车挂钩车上用座椅背隐藏式车载手机磁性支架多功能车内用品 ', 0, '8');
INSERT INTO `product` VALUES ('166', '汽车保平安弥勒佛摆件小车用品创意香水座车上高档车内可爱装饰品 ', 118, 78, 'products/1/qiche07.jpg', '2018-04-28', 0, '汽车保平安弥勒佛摆件小车用品创意香水座车上高档车内可爱装饰品 ', 0, '8');
INSERT INTO `product` VALUES ('167', '公牛车载充电器 汽车点烟器式车充双USB智能快充头多功能一拖二', 123, 68, 'products/1/qiche08.jpg', '2018-04-26', 0, '公牛车载充电器 汽车点烟器式车充双USB智能快充头多功能一拖二', 0, '8');
INSERT INTO `product` VALUES ('168', '汽车坐垫 夏季 冰丝小车坐垫夏天汽车5座套 全包围四季通用车套 ', 467, 389, 'products/1/qiche09.jpg', '2018-03-24', 0, '汽车坐垫 夏季 冰丝小车坐垫夏天汽车5座套 全包围四季通用车套 ', 0, '8');
INSERT INTO `product` VALUES ('169', '创意挂式香水高档汽车用香薰车载香水饰品挂件法国精油持久除异味 ', 178, 146, 'products/1/qiche10.jpg', '2018-04-14', 0, '创意挂式香水高档汽车用香薰车载香水饰品挂件法国精油持久除异味 ', 0, '8');
INSERT INTO `product` VALUES ('17', '索尼(SONY) E6533 Z3+', 4099, 3999, 'products/1/c_0017.jpg', '2015-11-02', 0, '索尼(SONY) E6533 Z3+ 双卡双4G手机 防水防尘 涧湖绿索尼z3专业防水 2070万像素 移动联通双4G', 0, '1');
INSERT INTO `product` VALUES ('170', '卡饰社洗车拖把汽车扫灰尘掸子车用除尘擦车刷子汽车用品清洁工具', 55, 45, 'products/1/qiche11.jpg', '2018-04-27', 0, '卡饰社洗车拖把汽车扫灰尘掸子车用除尘擦车刷子汽车用品清洁工具', 0, '8');
INSERT INTO `product` VALUES ('18', 'HTC One M9+', 3599, 3499, 'products/1/c_0018.jpg', '2015-11-02', 0, 'HTC One M9+（M9pw） 金银汇 移动联通双4G手机5.2英寸，8核CPU，指纹识别，UltraPixel超像素前置相机+2000万/200万后置双镜头相机！降价特卖，惊喜不断！', 0, '1');
INSERT INTO `product` VALUES ('19', 'HTC Desire 826d 32G 臻珠白', 1599, 1469, 'products/1/c_0020.jpg', '2015-11-02', 0, '后置1300万+UltraPixel超像素前置摄像头+【双】前置扬声器+5.5英寸【1080p】大屏！', 0, '1');
INSERT INTO `product` VALUES ('2', '中兴 AXON', 2899, 2699, 'products/1/c_0002.jpg', '2015-11-05', 0, '中兴 AXON 天机 mini 压力屏版 B2015 华尔金 移动联通电信4G 双卡双待', 0, '1');
INSERT INTO `product` VALUES ('20', '小米 红米2A 增强版 白色', 649, 549, 'products/1/c_0019.jpg', '2015-11-02', 0, '新增至2GB 内存+16GB容量！4G双卡双待，联芯 4 核 1.5GHz 处理器！', 0, '1');
INSERT INTO `product` VALUES ('21', '魅族 魅蓝note2 16GB 白色', 1099, 999, 'products/1/c_0021.jpg', '2015-11-02', 0, '现货速抢，抢完即止！5.5英寸1080P分辨率屏幕，64位八核1.3GHz处理器，1300万像素摄像头，双色温双闪光灯！', 0, '1');
INSERT INTO `product` VALUES ('22', '三星 Galaxy S5 (G9008W) 闪耀白', 2099, 1999, 'products/1/c_0022.jpg', '2015-11-02', 0, '5.1英寸全高清炫丽屏，2.5GHz四核处理器，1600万像素', 0, '1');
INSERT INTO `product` VALUES ('23', 'sonim XP7700 4G手机', 1799, 1699, 'products/1/c_0023.jpg', '2015-11-09', 0, '三防智能手机 移动/联通双4G 安全 黑黄色 双4G美国军工IP69 30天长待机 3米防水防摔 北斗', 0, '1');
INSERT INTO `product` VALUES ('24', '努比亚（nubia）Z9精英版 金色', 3988, 3888, 'products/1/c_0024.jpg', '2015-11-02', 0, '移动联通电信4G手机 双卡双待真正的无边框！金色尊贵版！4GB+64GB大内存！', 0, '1');
INSERT INTO `product` VALUES ('25', 'Apple iPhone 6 Plus (A1524) 16GB 金色', 5188, 4988, 'products/1/c_0025.jpg', '2015-11-02', 0, 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', 0, '1');
INSERT INTO `product` VALUES ('26', 'Apple iPhone 6s (A1700) 64G 玫瑰金色', 6388, 6088, 'products/1/c_0026.jpg', '2015-11-02', 1, 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', 0, '1');
INSERT INTO `product` VALUES ('27', '三星 Galaxy Note5（N9200）32G版', 5588, 5388, 'products/1/c_0027.jpg', '2015-11-02', 0, '旗舰机型！5.7英寸大屏，4+32G内存！不一样的SPen更优化的浮窗指令！赠无线充电板！', 0, '1');
INSERT INTO `product` VALUES ('28', '三星 Galaxy S6 Edge+（G9280）32G版 铂光金', 5999, 5888, 'products/1/c_0028.jpg', '2015-11-02', 0, '赠移动电源+自拍杆+三星OTG金属U盘+无线充电器+透明保护壳', 0, '1');
INSERT INTO `product` VALUES ('29', 'LG G4（H818）陶瓷白 国际版', 3018, 2978, 'products/1/c_0029.jpg', '2015-11-02', 0, '李敏镐代言，F1.8大光圈1600万后置摄像头，5.5英寸2K屏，3G+32G内存，LG年度旗舰机！', 0, '1');
INSERT INTO `product` VALUES ('3', '华为荣耀6', 1599, 1499, 'products/1/c_0003.jpg', '2015-11-02', 0, '荣耀 6 (H60-L01) 3GB内存标准版 黑色 移动4G手机', 0, '1');
INSERT INTO `product` VALUES ('30', '微软(Microsoft) Lumia 640 LTE DS (RM-1113)', 1099, 999, 'products/1/c_0030.jpg', '2015-11-02', 0, '微软首款双网双卡双4G手机，5.0英寸高清大屏，双网双卡双4G！', 0, '1');
INSERT INTO `product` VALUES ('31', '宏碁（acer）ATC705-N50 台式电脑', 2399, 2299, 'products/1/c_0031.jpg', '2015-11-02', 0, '爆款直降，满千减百，品质宏碁，特惠来袭，何必苦等11.11，早买早便宜！', 0, '2');
INSERT INTO `product` VALUES ('32', 'Apple MacBook Air MJVE2CH/A 13.3英寸', 6788, 6688, 'products/1/c_0032.jpg', '2015-11-02', 1, '宽屏笔记本电脑 128GB 闪存', 0, '2');
INSERT INTO `product` VALUES ('33', '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)', 4399, 4199, 'products/1/c_0033.jpg', '2015-11-02', 0, '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)14英寸笔记本电脑(i5-4210U 4G 500G 2G独显 Win8.1)', 0, '2');
INSERT INTO `product` VALUES ('34', '联想（Lenovo）小新V3000经典版', 4599, 4499, 'products/1/c_0034.jpg', '2015-11-02', 0, '14英寸超薄笔记本电脑（i7-5500U 4G 500G+8G SSHD 2G独显 全高清屏）黑色满1000減100，狂减！火力全开，横扫3天！', 0, '2');
INSERT INTO `product` VALUES ('35', '华硕（ASUS）经典系列R557LI', 3799, 3699, 'products/1/c_0035.jpg', '2015-11-02', 0, '15.6英寸笔记本电脑（i5-5200U 4G 7200转500G 2G独显 D刻 蓝牙 Win8.1 黑色）', 0, '2');
INSERT INTO `product` VALUES ('36', '华硕（ASUS）X450J', 4599, 4399, 'products/1/c_0036.jpg', '2015-11-02', 0, '14英寸笔记本电脑 （i5-4200H 4G 1TB GT940M 2G独显 蓝牙4.0 D刻 Win8.1 黑色）', 0, '2');
INSERT INTO `product` VALUES ('37', '戴尔（DELL）灵越 飞匣3000系列', 3399, 3299, 'products/1/c_0037.jpg', '2015-11-03', 1, ' Ins14C-4528B 14英寸笔记本（i5-5200U 4G 500G GT820M 2G独显 Win8）黑', 0, '2');
INSERT INTO `product` VALUES ('38', '惠普(HP)WASD 暗影精灵', 5699, 5499, 'products/1/c_0038.jpg', '2015-11-02', 0, '15.6英寸游戏笔记本电脑(i5-6300HQ 4G 1TB+128G SSD GTX950M 4G独显 Win10)', 0, '2');
INSERT INTO `product` VALUES ('39', 'Apple 配备 Retina 显示屏的 MacBook', 11299, 10288, 'products/1/c_0039.jpg', '2015-11-02', 0, 'Pro MF840CH/A 13.3英寸宽屏笔记本电脑 256GB 闪存', 0, '2');
INSERT INTO `product` VALUES ('4', '联想 P1', 2199, 1999, 'products/1/c_0004.jpg', '2015-11-02', 0, '联想 P1 16G 伯爵金 移动联通4G手机充电5分钟，通话3小时！科技源于超越！品质源于沉淀！5000mAh大电池！高端商务佳配！', 0, '1');
INSERT INTO `product` VALUES ('40', '机械革命（MECHREVO）MR X6S-M', 6799, 6599, 'products/1/c_0040.jpg', '2015-11-02', 0, '15.6英寸游戏本(I7-4710MQ 8G 64GSSD+1T GTX960M 2G独显 IPS屏 WIN7)黑色', 0, '2');
INSERT INTO `product` VALUES ('41', '神舟（HASEE） 战神K660D-i7D2', 5699, 5499, 'products/1/c_0041.jpg', '2015-11-02', 0, '15.6英寸游戏本(i7-4710MQ 8G 1TB GTX960M 2G独显 1080P)黑色', 0, '2');
INSERT INTO `product` VALUES ('42', '微星（MSI）GE62 2QC-264XCN', 6199, 5999, 'products/1/c_0042.jpg', '2015-11-02', 0, '15.6英寸游戏笔记本电脑（i5-4210H 8G 1T GTX960MG DDR5 2G 背光键盘）黑色', 0, '2');
INSERT INTO `product` VALUES ('43', '雷神（ThundeRobot）G150S', 5699, 5499, 'products/1/c_0043.jpg', '2015-11-02', 0, '15.6英寸游戏本 ( i7-4710MQ 4G 500G GTX950M 2G独显 包无亮点全高清屏) 金', 0, '2');
INSERT INTO `product` VALUES ('44', '惠普（HP）轻薄系列 HP', 3199, 3099, 'products/1/c_0044.jpg', '2015-11-02', 0, '15-r239TX 15.6英寸笔记本电脑（i5-5200U 4G 500G GT820M 2G独显 win8.1）金属灰', 0, '2');
INSERT INTO `product` VALUES ('45', '未来人类（Terrans Force）T5', 10999, 9899, 'products/1/c_0045.jpg', '2015-11-02', 0, '15.6英寸游戏本（i7-5700HQ 16G 120G固态+1TB GTX970M 3G GDDR5独显）黑', 0, '2');
INSERT INTO `product` VALUES ('46', '戴尔（DELL）Vostro 3800-R6308 台式电脑', 3299, 3199, 'products/1/c_0046.jpg', '2015-11-02', 0, '（i3-4170 4G 500G DVD 三年上门服务 Win7）黑', 0, '2');
INSERT INTO `product` VALUES ('47', '联想（Lenovo）H3050 台式电脑', 5099, 4899, 'products/1/c_0047.jpg', '2015-11-11', 0, '（i5-4460 4G 500G GT720 1G独显 DVD 千兆网卡 Win10）23英寸', 0, '2');
INSERT INTO `product` VALUES ('48', 'Apple iPad mini 2 ME279CH/A', 2088, 1888, 'products/1/c_0048.jpg', '2015-11-02', 0, '（配备 Retina 显示屏 7.9英寸 16G WLAN 机型 银色）', 0, '2');
INSERT INTO `product` VALUES ('49', '小米（MI）7.9英寸平板', 1399, 1299, 'products/1/c_0049.jpg', '2015-11-02', 0, 'WIFI 64GB（NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536视网膜屏 800W）白色', 0, '2');
INSERT INTO `product` VALUES ('5', '摩托罗拉 moto x（x+1）', 1799, 1699, 'products/1/c_0005.jpg', '2015-11-01', 0, '摩托罗拉 moto x（x+1）(XT1085) 32GB 天然竹 全网通4G手机11月11天！MOTO X震撼特惠来袭！1699元！带你玩转黑科技！天然材质，原生流畅系统！', 0, '1');
INSERT INTO `product` VALUES ('50', 'Apple iPad Air 2 MGLW2CH/A', 2399, 2299, 'products/1/c_0050.jpg', '2015-11-12', 1, '（9.7英寸 16G WLAN 机型 银色）', 0, '2');
INSERT INTO `product` VALUES ('51', '几立廿三里小脚九分裤黑男士纯棉薄日系休闲裤秋修身百搭裤子潮 ', 149, 87, 'products/1/yifu01.jpg', '2018-05-08', 0, '几立廿三里小脚九分裤黑男士纯棉薄日系休闲裤秋修身百搭裤子潮 ', 0, '3');
INSERT INTO `product` VALUES ('52', 'PROS BY CH 复古龙虎刺绣横须贺工装夹克男日系短款宽松衬衫外套', 399, 359, 'products/1/yifu02.jpg', '2018-05-15', 0, 'PROS BY CH 复古龙虎刺绣横须贺工装夹克男日系短款宽松衬衫外套', 0, '3');
INSERT INTO `product` VALUES ('53', '日系文艺蓝条纹衬衫男春季薄款情侣休闲长袖衬衣外套 ', 238, 188, 'products/1/yifu03.jpg', '2018-05-09', 0, '日系文艺蓝条纹衬衫男春季薄款情侣休闲长袖衬衣外套 ', 0, '3');
INSERT INTO `product` VALUES ('54', '单穿内搭都很抢眼 高街风镂空网纱小网格五分袖撞色半袖刺绣tee恤 ', 135, 88, 'products/1/yifu04.jpg', '2018-05-01', 0, '单穿内搭都很抢眼 高街风镂空网纱小网格五分袖撞色半袖刺绣tee恤 ', 0, '3');
INSERT INTO `product` VALUES ('55', '启法中式男装棉麻风衣男春季中长款休闲百搭黑色中国风外套男宽松 ', 356, 312, 'products/1/yifu05.jpg', '2018-05-02', 0, '启法中式男装棉麻风衣男春季中长款休闲百搭黑色中国风外套男宽松 ', 0, '3');
INSERT INTO `product` VALUES ('56', 'PSO Brand 18SS宽松连帽卫衣纯色拼接印花帽衫中领拉链休闲外套男 ', 298, 268, 'products/1/yifu06.jpg', '2018-05-02', 0, 'PSO Brand 18SS宽松连帽卫衣纯色拼接印花帽衫中领拉链休闲外套男 ', 0, '3');
INSERT INTO `product` VALUES ('57', '原创设计水桶裤流苏脚口灯芯绒垮裤帅气宽松直筒休闲裤男 ', 256, 225, 'products/1/yifu07.jpg', '2018-05-03', 0, '原创设计水桶裤流苏脚口灯芯绒垮裤帅气宽松直筒休闲裤男 ', 0, '3');
INSERT INTO `product` VALUES ('58', '2018春季新款仙气网纱半身裙 不规则显瘦a字裙 ', 199, 178, 'products/1/yifu08.jpg', '2018-05-13', 0, '2018春季新款仙气网纱半身裙 不规则显瘦a字裙 ', 0, '3');
INSERT INTO `product` VALUES ('59', '复古温柔风连衣裙 碎花雪纺初恋裙少女sukol2018夏新款 ', 149, 139, 'products/1/yifu09.jpg', '2018-05-12', 1, '复古温柔风连衣裙 碎花雪纺初恋裙少女sukol2018夏新款 ', 0, '3');
INSERT INTO `product` VALUES ('6', '魅族 MX5 16GB 银黑色', 1899, 1799, 'products/1/c_0006.jpg', '2015-11-02', 0, '魅族 MX5 16GB 银黑色 移动联通双4G手机 双卡双待送原厂钢化膜+保护壳+耳机！5.5英寸大屏幕，3G运行内存，2070万+500万像素摄像头！长期省才是真的省！', 0, '1');
INSERT INTO `product` VALUES ('60', '韩版chic高腰牛仔裤女紧身小脚九分裤2018春季新款显瘦铅笔裤 ', 108, 79, 'products/1/yifu10.jpg', '2018-04-27', 0, '韩版chic高腰牛仔裤女紧身小脚九分裤2018春季新款显瘦铅笔裤 ', 0, '3');
INSERT INTO `product` VALUES ('61', '2018新款高腰波点雪纺百褶半身裙短裙女夏学生A字裤裙裙子 ', 98, 78, 'products/1/yifu11.jpg', '2018-05-15', 0, '2018新款高腰波点雪纺百褶半身裙短裙女夏学生A字裤裙裙子 ', 0, '3');
INSERT INTO `product` VALUES ('62', '2017新款复古学院风宽松翻领中长款女毛呢外套chic大衣女冬呢子', 390, 255, 'products/1/yifu12.jpg', '2018-05-09', 0, '2017新款复古学院风宽松翻领中长款女毛呢外套chic大衣女冬呢子', 0, '3');
INSERT INTO `product` VALUES ('63', '不会腻的俏皮feel 圆领三粒扣美澳全羊毛双面呢外套 ', 789, 589, 'products/1/yifu13.jpg', '2018-05-04', 0, '不会腻的俏皮feel 圆领三粒扣美澳全羊毛双面呢外套 ', 0, '3');
INSERT INTO `product` VALUES ('64', '2018春装新款宽松真丝风衣女收腰过膝休闲chic中长款外套 ', 468, 398, 'products/1/yifu14.jpg', '2018-04-29', 1, '2018春装新款宽松真丝风衣女收腰过膝休闲chic中长款外套 ', 0, '3');
INSERT INTO `product` VALUES ('65', 'FNNKA日系潮流刺绣短袖T恤男情侣夏装ulzzang韩版学生纯棉男士衫', 98, 88, 'products/1/yifu15.jpg', '2018-05-14', 0, 'FNNKA日系潮流刺绣短袖T恤男情侣夏装ulzzang韩版学生纯棉男士衫', 0, '3');
INSERT INTO `product` VALUES ('66', '连帽夹克防风防水纸复古街头潮情侣套头宽松休闲冲锋衣 ', 368, 325, 'products/1/yifu16.jpg', '2018-05-02', 1, '连帽夹克防风防水纸复古街头潮情侣套头宽松休闲冲锋衣 ', 0, '3');
INSERT INTO `product` VALUES ('67', '2018韩版新款夏装ins短袖t恤女白色学生半袖打底衫女裝上衣体恤潮 ', 108, 78, 'products/1/yifu17.jpg', '2018-05-11', 0, '2018韩版新款夏装ins短袖t恤女白色学生半袖打底衫女裝上衣体恤潮 ', 0, '3');
INSERT INTO `product` VALUES ('68', '夏季ol职业装女装套装裙女士西装短袖衬衫面试正装美容酒店工作服 ', 588, 458, 'products/1/yifu18.jpg', '2018-05-10', 0, '夏季ol职业装女装套装裙女士西装短袖衬衫面试正装美容酒店工作服 ', 0, '3');
INSERT INTO `product` VALUES ('69', '职业装女装套装2018新款西装套裙正装2017时尚气质酒店前台工作服 ', 689, 469, 'products/1/yifu19.jpg', '2018-05-08', 0, '职业装女装套装2018新款西装套裙正装2017时尚气质酒店前台工作服 ', 0, '3');
INSERT INTO `product` VALUES ('7', '三星 Galaxy On7', 1499, 1398, 'products/1/c_0007.jpg', '2015-11-14', 0, '三星 Galaxy On7（G6000）昂小七 金色 全网通4G手机 双卡双待新品火爆抢购中！京东尊享千元良机！5.5英寸高清大屏！1300+500W像素！评价赢30元话费券！', 0, '1');
INSERT INTO `product` VALUES ('70', '西装男套装修身工作服韩版英伦风三件套正装职业商务绅士伴郎新郎 ', 899, 658, 'products/1/yifu20.jpg', '2018-05-10', 0, '西装男套装修身工作服韩版英伦风三件套正装职业商务绅士伴郎新郎 ', 0, '3');
INSERT INTO `product` VALUES ('71', '亚麻西服男商务休闲西装男外套春夏季薄款修身棉麻男士上衣 ', 534, 436, 'products/1/yifu21.jpg', '2018-05-12', 0, '亚麻西服男商务休闲西装男外套春夏季薄款修身棉麻男士上衣 ', 0, '3');
INSERT INTO `product` VALUES ('72', ' 冰丝阔腿裤女夏季喇叭裤女高腰长裤子宽松显瘦薄款垂感天丝牛仔裤', 268, 158, 'products/1/yifu22.jpg', '2018-05-04', 0, ' 冰丝阔腿裤女夏季喇叭裤女高腰长裤子宽松显瘦薄款垂感天丝牛仔裤', 0, '3');
INSERT INTO `product` VALUES ('73', '花花公子夏季男士牛仔裤男薄款2018新款小清新九分裤男修身小脚裤 ', 176, 132, 'products/1/yifu23.jpg', '2018-05-12', 0, '花花公子夏季男士牛仔裤男薄款2018新款小清新九分裤男修身小脚裤 ', 0, '3');
INSERT INTO `product` VALUES ('74', 'ONLY2018夏季新款两件套分身休闲裤女|118178505 ', 649, 459, 'products/1/yifu24.jpg', '2018-05-14', 0, 'ONLY2018夏季新款两件套分身休闲裤女|118178505 ', 0, '3');
INSERT INTO `product` VALUES ('75', '而立家具 北欧布艺羽绒沙发组合可拆洗双三人日式小户型客厅沙发 ', 1235, 879, 'products/1/jiaju01.jpg', '2018-05-10', 0, '而立家具 北欧布艺羽绒沙发组合可拆洗双三人日式小户型客厅沙发 ', 0, '4');
INSERT INTO `product` VALUES ('76', '欧式沙发客厅转角大小户型简欧奢华整装可拆洗皮布艺沙发组合家具 ', 5189, 3897, 'products/1/jiaju02.jpg', '2018-05-03', 0, '欧式沙发客厅转角大小户型简欧奢华整装可拆洗皮布艺沙发组合家具 ', 0, '4');
INSERT INTO `product` VALUES ('77', '欧式餐桌椅组合现代简约大理石实木长方形伸缩圆桌折叠小户型家具 ', 2960, 1980, 'products/1/jiaju03.jpg', '2018-05-11', 0, '欧式餐桌椅组合现代简约大理石实木长方形伸缩圆桌折叠小户型家具 ', 0, '4');
INSERT INTO `product` VALUES ('78', '儿童床女孩公主床1.2米实木小学生少女高箱储物单人床卧室家具1.5 ', 2150, 1580, 'products/1/jiaju04.jpg', '2018-05-08', 0, '儿童床女孩公主床1.2米实木小学生少女高箱储物单人床卧室家具1.5 ', 0, '4');
INSERT INTO `product` VALUES ('79', '新中式实木沙发组合禅意古典罗汉床沙发客厅整装布艺沙发家具定制 ', 1580, 1370, 'products/1/jiaju05.jpg', '2018-05-11', 0, '新中式实木沙发组合禅意古典罗汉床沙发客厅整装布艺沙发家具定制 ', 0, '4');
INSERT INTO `product` VALUES ('8', 'NUU NU5', 1288, 1190, 'products/1/c_0008.jpg', '2015-11-02', 0, 'NUU NU5 16GB 移动联通双4G智能手机 双卡双待 晒单有礼 晨光金香港品牌 2.5D弧度前后钢化玻璃 随机附赠手机套+钢化贴膜 晒单送移动电源+蓝牙耳机', 0, '1');
INSERT INTO `product` VALUES ('80', '美式乡村实木电视柜茶几组合简约复古做旧电视机柜客厅储物柜地柜 ', 1890, 1360, 'products/1/jiaju06.jpg', '2018-05-03', 0, '美式乡村实木电视柜茶几组合简约复古做旧电视机柜客厅储物柜地柜 ', 0, '4');
INSERT INTO `product` VALUES ('81', ' 电脑桌台式家用 简约经济型 办公桌子简易书桌学生写字台 ', 599, 489, 'products/1/jiaju07.jpg', '2018-05-11', 1, ' 电脑桌台式家用 简约经济型 办公桌子简易书桌学生写字台 ', 0, '4');
INSERT INTO `product` VALUES ('82', '全纯实木餐桌椅组合现代简约折叠可伸缩圆桌实木餐桌家用橡木饭桌', 1888, 1488, 'products/1/jiaju08.jpg', '2018-05-07', 0, '全纯实木餐桌椅组合现代简约折叠可伸缩圆桌实木餐桌家用橡木饭桌', 0, '4');
INSERT INTO `product` VALUES ('83', '苏泊尔电饭煲锅IH电磁加热智能4L球釜饭锅正品家用3-4-5-6人正品', 1299, 988, 'products/1/jiaju09.jpg', '2018-05-02', 0, '苏泊尔电饭煲锅IH电磁加热智能4L球釜饭锅正品家用3-4-5-6人正品', 0, '4');
INSERT INTO `product` VALUES ('84', 'Joyoung/九阳 DJ12B-A603DG豆浆机全自动家用多功能正品豆将全钢 ', 189, 159, 'products/1/jiaju10.jpg', '2018-05-11', 0, 'Joyoung/九阳 DJ12B-A603DG豆浆机全自动家用多功能正品豆将全钢 ', 0, '4');
INSERT INTO `product` VALUES ('85', '苏泊尔电蒸锅多功能家用蒸气锅三层大容量电蒸笼蒸锅蒸菜火锅正品 ', 599, 389, 'products/1/jiaju11.jpg', '2018-05-04', 0, '苏泊尔电蒸锅多功能家用蒸气锅三层大容量电蒸笼蒸锅蒸菜火锅正品 ', 0, '4');
INSERT INTO `product` VALUES ('86', '小天鹅TD100V81WDG大容量家用10公斤kg变频洗烘一体滚筒洗衣机', 4998, 3899, 'products/1/jiaju12.jpg', '2018-05-04', 0, '小天鹅TD100V81WDG大容量家用10公斤kg变频洗烘一体滚筒洗衣机', 0, '4');
INSERT INTO `product` VALUES ('87', 'Haier/海尔 EG8014HB919SU1 8公斤变频洗烘一体滚筒洗衣机', 4999, 3299, 'products/1/jiaju13.jpg', '2018-05-09', 0, 'Haier/海尔 EG8014HB919SU1 8公斤变频洗烘一体滚筒洗衣机', 0, '4');
INSERT INTO `product` VALUES ('88', 'Xiaomi/小米 小米电视4A 65英寸4k超高清智能平板液晶电视机6070', 4499, 3499, 'products/1/jiaju14.jpg', '2018-05-14', 0, 'Xiaomi/小米 小米电视4A 65英寸4k超高清智能平板液晶电视机6070', 0, '4');
INSERT INTO `product` VALUES ('89', 'Hisense/海信 LED55E7CY 55英寸4K曲面智能网络平板液晶电视机60', 4599, 3599, 'products/1/jiaju15.jpg', '2018-05-11', 0, 'Hisense/海信 LED55E7CY 55英寸4K曲面智能网络平板液晶电视机60', 0, '4');
INSERT INTO `product` VALUES ('9', '乐视（Letv）乐1pro（X800）', 2399, 2299, 'products/1/c_0009.jpg', '2015-11-02', 0, '乐视（Letv）乐1pro（X800）64GB 金色 移动联通4G手机 双卡双待乐视生态UI+5.5英寸2K屏+高通8核处理器+4GB运行内存+64GB存储+1300万摄像头！', 0, '1');
INSERT INTO `product` VALUES ('90', 'Samsung/三星 UA55MUF30ZJXXZ 55英寸4K超高清液晶智能电视机', 4199, 3499, 'products/1/jiaju16.jpg', '2018-05-02', 0, 'Samsung/三星 UA55MUF30ZJXXZ 55英寸4K超高清液晶智能电视机', 0, '4');
INSERT INTO `product` VALUES ('91', 'Haier/海尔 BCD-572WDENU1 572升WIFI智能变频风冷无霜对开门冰箱', 4099, 3699, 'products/1/jiaju17.jpg', '2018-05-08', 0, 'Haier/海尔 BCD-572WDENU1 572升WIFI智能变频风冷无霜对开门冰箱', 0, '4');
INSERT INTO `product` VALUES ('92', '电冰箱家用三门节能小型风冷无霜双开门Midea/美的 BCD-215WTM(E)', 2599, 1799, 'products/1/jiaju18.jpg', '2018-05-03', 0, '电冰箱家用三门节能小型风冷无霜双开门Midea/美的 BCD-215WTM(E)', 0, '4');
INSERT INTO `product` VALUES ('93', '日式实木五斗柜抽屉小户型卧室北欧原木家具现代简约橡木储物斗橱 ', 2899, 1589, 'products/1/jiaju19.jpg', '2018-05-10', 0, '日式实木五斗柜抽屉小户型卧室北欧原木家具现代简约橡木储物斗橱 ', 0, '4');
INSERT INTO `product` VALUES ('94', '欢乐颂同款椅 会议椅子简约 现代办公椅家用 实木真皮老板电脑椅 ', 820, 689, 'products/1/jiaju20.jpg', '2018-05-04', 0, '欢乐颂同款椅 会议椅子简约 现代办公椅家用 实木真皮老板电脑椅 ', 0, '4');
INSERT INTO `product` VALUES ('95', '实木高低床子母床双层床上下床儿童床成人母子床上下铺榉木家具 ', 3689, 2578, 'products/1/jiaju21.jpg', '2018-05-02', 0, '实木高低床子母床双层床上下床儿童床成人母子床上下铺榉木家具 ', 0, '4');
INSERT INTO `product` VALUES ('96', '亮片婚鞋蕾丝高跟鞋浅色尖头细跟ol单鞋女细跟婚纱照新娘鞋宴会鞋 ', 256, 128, 'products/1/xiexue01.jpg', '2018-05-09', 0, ' 亮片婚鞋蕾丝高跟鞋浅色尖头细跟ol单鞋女细跟婚纱照新娘鞋宴会鞋 ', 0, '5');
INSERT INTO `product` VALUES ('97', '2017春季新款尖头粗跟高跟鞋一字扣带中空高跟鞋女粗跟凉鞋', 235, 189, 'products/1/xiexue02.jpg', '2017-07-16', 0, '2017春季新款尖头粗跟高跟鞋一字扣带中空高跟鞋女粗跟凉鞋', 0, '5');
INSERT INTO `product` VALUES ('98', '韩版坡跟时尚铆钉凉鞋女夏新款学生露趾一字扣带高跟罗马鞋沙滩鞋', 295, 148, 'products/1/xiexue03.jpg', '2018-05-11', 0, '韩版坡跟时尚铆钉凉鞋女夏新款学生露趾一字扣带高跟罗马鞋沙滩鞋', 0, '5');
INSERT INTO `product` VALUES ('99', '老爹鞋女运动鞋ulzzang原宿zipper鞋憨憨鞋2018百搭ins超火的鞋子', 368, 312, 'products/1/xiexue04.jpg', '2018-05-02', 0, '老爹鞋女运动鞋ulzzang原宿zipper鞋憨憨鞋2018百搭ins超火的鞋子', 0, '5');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('373eb242933b4f5ca3bd43503c34668b', 'ccc', 'ccc', '测试', 'bbb@store.com', '15723689921', '2015-11-04', '男', 1, '9782f3e837ff422b9aee8b6381ccf927bdd9d2ced10d48f4ba4b9f187edf7738');
INSERT INTO `user` VALUES ('3ca76a75e4f64db2bacd0974acc7c897', 'zhangsan', 'zhangsan', '张三', 'zhangsan@store.com', '15723689921', '1990-02-01', '男', 1, '1258e96181a9457987928954825189000bae305094a042d6bd9d2d35674684e6');
INSERT INTO `user` VALUES ('62145f6e66ea4f5cbe7b6f6b954917d3', 'lisi', 'lisi', '李四', 'lisi@store.com', '15723689921', '2015-11-03', '男', 1, '19f100aa81184c03951c4b840a725b6a98097aa1106a4a38ba1c29f1a496c231');
INSERT INTO `user` VALUES ('70F7044604D04F3482C1D32355C05B04', 'xiaohong', 'xiaohong', '小红', 'xiaohong@qq.com', '12324343434', '2000-06-09', '女', 1, 'C4111C07A55D4D80983B95C8F76532D1');
INSERT INTO `user` VALUES ('8DADCBADACA04BF08F528FA29FEAAF6D', 'xiaoming', 'xiaoming', '小明', 'xiaoming@qq.com', '16565645433', '2007-01-31', '男', 0, '45884E6ED21245D8B8576B414544CF69');
INSERT INTO `user` VALUES ('90CF1AA0B4B846008C9BE8DF3B058F59', 'zhangliu', 'zhangliu', '赵六', 'zhangliu@qq.com', '12334345455', '2003-05-09', '男', 1, 'F570D1E8832248CEA2E73079AB958E02');
INSERT INTO `user` VALUES ('c95b15a864334adab3d5bb6604c6e1fc', 'wangwu', 'wangwu', '王五', 'wangwu@store.com', '15712344823', '2000-02-01', '男', 0, '71a3a933353347a4bcacff699e6baa9c950a02f6b84e4f6fb8404ca06febfd6f');

SET FOREIGN_KEY_CHECKS = 1;
