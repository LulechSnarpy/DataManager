# DataManager

<<<<<<< HEAD
## 项目使用eclipse编写 jdk1.7以上可以使用

## 执行的样例在runing包下：
  ### 1. ConvertImageFileRun 
      功能：格式转换 png转jpg文件 不改变文件名称
  ### 2. FileReNameRun
      功能：改变文件名称
  ### 3. Iou_files_Run3
      功能:根据识别难度、遮挡面积、阴影筛选图片，识别难度低，遮挡面积小，没有阴影的图片
  ### 4. Iou_Operation3_Run
      功能:计算不同IOU下图片的召回率，根据图片pro数量展示召回率，漏检数，漏检率
  ### 5. Iou_Operation4_Run
      功能:计算不同IOU下图片的召回率，根据图片pro数量展示准确率。全部pro的准确率
  ### 6. Iou_Peration_Run
      功能:计算单个文件的IOU和召回率
  ### 7. PhotoChooser2Test
      功能:计算批量文件的不同IOU下的召回率并筛选出符合要求的文件 IOU【0.9,0.7,0.5】 筛选全部iou0.9以上是pass，0.5一下为fail
  ### 8. RankProByIouRun
      功能:将proposal根据iou排序并输出结果
  ### 9. TryToCorrectRun
      功能:修正之前软件产生的xml文件的错误执行程序 情况:object数量异常
  ### 10. XmlFormatChangerAllRun
      功能:改变之前生成的xml中部分的字符大小问题  具体请修改XmlWriter文件中的输出
=======
## 椤圭洰浣跨敤eclipse缂栧啓 jdk1.7浠ヤ笂鍙互浣跨敤

## 鎵ц鐨勬牱渚嬪湪runing鍖呬笅锛�
  ### 1. ConvertImageFileRun 
      鍔熻兘锛氭牸寮忚浆鎹� png杞琷pg鏂囦欢 涓嶆敼鍙樻枃浠跺悕绉�
  ### 2. FileReNameRun
      鍔熻兘锛氭敼鍙樻枃浠跺悕绉�
  ### 3. Iou_files_Run3
      鍔熻兘:鏍规嵁璇嗗埆闅惧害銆侀伄鎸￠潰绉�侀槾褰辩瓫閫夊浘鐗囷紝璇嗗埆闅惧害浣庯紝閬尅闈㈢Н灏忥紝娌℃湁闃村奖鐨勫浘鐗�
  ### 4. Iou_Operation3_Run
      鍔熻兘:璁＄畻涓嶅悓IOU涓嬪浘鐗囩殑鍙洖鐜囷紝鏍规嵁鍥剧墖pro鏁伴噺灞曠ず鍙洖鐜囷紝婕忔鏁帮紝婕忔鐜�
  ### 5. Iou_Operation4_Run
      鍔熻兘:璁＄畻涓嶅悓IOU涓嬪浘鐗囩殑鍙洖鐜囷紝鏍规嵁鍥剧墖pro鏁伴噺灞曠ず鍑嗙‘鐜囥�傚叏閮╬ro鐨勫噯纭巼
  ### 6. Iou_Peration_Run
      鍔熻兘:璁＄畻鍗曚釜鏂囦欢鐨処OU鍜屽彫鍥炵巼
  ### 7. PhotoChooser2Test
      鍔熻兘:璁＄畻鎵归噺鏂囦欢鐨勪笉鍚孖OU涓嬬殑鍙洖鐜囧苟绛涢�夊嚭绗﹀悎瑕佹眰鐨勬枃浠� IOU銆�0.9,0.7,0.5銆� 绛涢�夊叏閮╥ou0.9浠ヤ笂鏄痯ass锛�0.5涓�涓嬩负fail
  ### 8. RankProByIouRun
      鍔熻兘:灏唒roposal鏍规嵁iou鎺掑簭骞惰緭鍑虹粨鏋�
  ### 9. TryToCorrectRun
      鍔熻兘:淇涔嬪墠杞欢浜х敓鐨剎ml鏂囦欢鐨勯敊璇墽琛岀▼搴� 鎯呭喌:object鏁伴噺寮傚父
  ### 10. XmlFormatChangerAllRun
      鍔熻兘:鏀瑰彉涔嬪墠鐢熸垚鐨剎ml涓儴鍒嗙殑瀛楃澶у皬闂  鍏蜂綋璇蜂慨鏀筙mlWriter鏂囦欢涓殑杈撳嚭
>>>>>>> 0b715969038d2d41a987ccba6421d37f4f37401c
