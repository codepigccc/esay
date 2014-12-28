package com.esay.comm;

/**
 * 静态常量类
 * @author Administrator
 *
 */
public class C
{
   public static final String RESULT_SUCCESS_CODE = "00000000";
   public static final String AppFileName="";
   public static final String APPPackageName="com.esay.app";

   public static final class LoginType
   {
	  public static final int NORMAL=10001;
	  public static final int WEIBO=10002;
	  public static final int WEIXIN=10003;
	  public static final int RENREN=10004;
   }
   public static final class ActivityJump
   {
	  public static final int LOGIN=100001;
	  public static final int SUBSCRIBE=100002;
	  public static final int Favorite=100003;
   }
   public static final class Intent_Key
   {
	  public static final String Subscribes = "Subscribes";
	  public static final String AlbumId="AlbumId";
	  public static final String SubId="SubId";
	  public static final String Name="Name";
	  public static final String Describe="Describe";
	  public static final String PictureId="PictureId";
	  public static final String LoginType="LoginType";
   }

   public static final class Key
   {
   }

   public static final class Setting
   {
	  public static final String USER_INFO="user";
	  public static final String UID="ddd";
	  public static final String USERNAME="uuu";
	  public static final String PASSWORD="ppp";
	  public static final String NICKNAME="nnn";
	  public static final String AVATARS="aaa";
   }

   public static final class Action
   {
   }

   public static final class Task
   {
	  public static final String TEST="Test";
	  public static final String LoadCommodityDetails = "LoadCommodityDetails";
	  public static final String GetCommoditysByFrequencyBarCode = "GetCommoditysByFrequencyBarCode";
	  public static final String BindCommodityByBarcode = "BindCommodityByBarcode";
	  public static final String BindCommodityByBarcodeSync="BindCommodityByBarcodeSync";
	  public static final String VerifyCommodityByBarcodeSync ="VerifyCommodityByBarcodeSync";
	  public static final String VerifyCommodityByBarcode = "VerifyCommodityByBarcode";
	  public static final String GetFrequencyList = "GetFrequencyList";
	  public static final String GetOperatorList = "GetOperatorList";
	  public static final String Outbound="Outbound";
	  
	  public static final String GetProcurementList="GetProcurementList";
	  public static final String GetRawMaterialDetails="GetRawMaterialDetails";
	  public static final String Warehouse="Warehouse";
	  
	  public static final String GetDelivertOrderList="GetDelivertOrderList";
	  public static final String GetRawMaterialDetailsStockOut="GetRawMaterialDetailsStockOut";
	  
	  public static final String GetFreeSelectCommodity="GetFreeSelectCommodity";
   }
}
