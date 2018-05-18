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
