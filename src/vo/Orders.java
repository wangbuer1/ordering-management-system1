package vo;

import java.util.List;

import factory.DAOFactory;

/**
 * 璁㈠崟绫�
 */
public class Orders {
	private int order_id;			//璁㈠崟ID
	private Person user;			//鐢ㄦ埛ID锛屽弬鐓х敤鎴风被
	private Menu menu;				//鑿滃崟ID锛屽弬鐓ц彍鍗曠被
	private int order_num;			//璁㈣喘鑿滃搧鏁伴噺
	private String order_notice;	//鍙ｅ懗瑕佹眰
	private String other_notice;	//鍏朵粬瑕佹眰
	private String states;			//璁㈠崟浠樻鐘舵��
	
	private StringBuffer showBuyList;		//鏄剧ず璐墿杞﹀垪琛�
	private int total;						//璐墿杞︽�绘暟閲�
	private int totalPrice;					//鎵�鏈夊晢鍝佹�讳环鏍�
	private StringBuffer showNoPayList;		//鏄剧ず鏈敮浠樺垪琛�
	private StringBuffer showPayList;		//鏄剧ず宸叉敮浠樺垪琛�
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public Person getUser() {
		return user;
	}
	public void setUser(Person user) {
		this.user = user;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getOrder_notice() {
		return order_notice;
	}
	public void setOrder_notice(String order_notice) {
		this.order_notice = order_notice;
	}
	public String getOther_notice() {
		return other_notice;
	}
	public void setOther_notice(String other_notice) {
		this.other_notice = other_notice;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	/**
	 * 鏄剧ず璐墿杞﹀垪琛�
	 * @return
	 */
	public StringBuffer getShowBuyList() {
		showBuyList = new StringBuffer();
		List<Orders> list = DAOFactory.getOrdersDAOInstance().searchBuy(user);
		for (Orders orders : list) {
			showBuyList.append("<tr class=\"tr\"><td class=\"td1\"><span class=\"no\">");
			//缂栧彿
			showBuyList.append(orders.getOrder_id());
			showBuyList.append("</span></td><td class=\"td1\"><span class=\"food\">");
			//鑿滃悕
			showBuyList.append(orders.getMenu().getMenu_name());
			showBuyList.append("</span></td><td class=\"td1\"><span class=\"number\">");
			//鏁伴噺
			showBuyList.append(orders.getOrder_num());
			showBuyList.append("</span></td><td class=\"td1\"><span class=\"per_price\">");
			//鍗曚环
			showBuyList.append(orders.getMenu().getMenu_price());
			showBuyList.append("</span></td><td class=\"td1\"><span class=\"price\">");
			//鎬讳环
			showBuyList.append(orders.getOrder_num()*orders.getMenu().getMenu_price());
			showBuyList.append("</span></td><td class=\"td1\"><span class=\"information\">");
			//澶囨敞锛氬彛鍛宠姹�+鍏朵粬瑕佹眰
			showBuyList.append(orders.getOrder_notice() + "," + orders.getOther_notice());
			showBuyList.append("</span></td><td class=\"td1\"><span class=\"delete\"><a href=\"DeleteBuyServlet?id=" + orders.getOrder_id() + "\">删除</a></span></td></tr>");
		}
//		<tr class="tr">
//      <td class="td1"><span class="no">1</span></td>
//      <td class="td1"><span class="food">鑿滃搧</span></td>
//      <td class="td1"><span class="number">1</span></td>
//      <td class="td1"><span class="per_price">19.0</span></td>
//      <td class="td1"><span class="price">19.0</span></td>
//      <td class="td1"><span class="information">鑿滃搧淇℃伅</span></td>
//      <td class="td1"><span class="delete"><a href="#">鍒犻櫎</a></span></td>
//  	</tr>
		return showBuyList;
	}
	/**
	 * 杩斿洖鎬绘暟閲�
	 * @return
	 */
	public int getTotal() {
		total = 0;
		List<Orders> list = DAOFactory.getOrdersDAOInstance().searchBuy(user);
		for (Orders orders : list) {
			total += orders.getOrder_num();
		}
		return total;
	}
	/**
	 * 鎵�鏈夊晢鍝佹�讳环鏍�
	 * @return
	 */
	public int getTotalPrice() {
		totalPrice = 0;
		List<Orders> list = DAOFactory.getOrdersDAOInstance().searchBuy(user);
		for (Orders orders : list) {
			totalPrice += orders.getOrder_num() * orders.getMenu().getMenu_price();
			System.out.println(totalPrice);
		}
		return totalPrice;
	}
	/**
	 * 鏄剧ず鏈敮浠樺垪琛�
	 * @return
	 */
	public StringBuffer getShowNoPayList() {
		showNoPayList = new StringBuffer();
		List<Orders> list = DAOFactory.getOrdersDAOInstance().searchBuy(user);
		for (Orders orders : list) {
			showNoPayList.append("<tr class=\"tr\"><td class=\"td1\"><span class=\"no\">");
			showNoPayList.append(orders.getOrder_id());
			showNoPayList.append("</span></td><td class=\"td1\"><span class=\"food\">");
			showNoPayList.append(orders.getMenu().getMenu_name());
			showNoPayList.append("</span></td><td class=\"td1\"><span class=\"number\">");
			showNoPayList.append(orders.getOrder_num());
			showNoPayList.append("</span></td><td class=\"td1\"><span class=\"per_price\">");
			showNoPayList.append(orders.getMenu().getMenu_price());
			showNoPayList.append("</span></td><td class=\"td1\"><span class=\"price\">");
			showNoPayList.append(orders.getMenu().getMenu_price() * orders.getOrder_num());
			showNoPayList.append("</span></td><td class=\"td1\"><span class=\"information\">");
			showNoPayList.append(orders.getOrder_notice() + "," + orders.getOther_notice());
		}
		return showNoPayList;
//		<tr class="tr">
//        <td class="td1"><span class="no">1</span></td>
//        <td class="td1"><span class="food">鑿滃搧</span></td>
//        <td class="td1"><span class="number">1</span></td>
//        <td class="td1"><span class="per_price">19.0</span></td>
//        <td class="td1"><span class="price">19.0</span></td>
//        <td class="td1"><span class="information">鑿滃搧淇℃伅</span></td>
//    	</tr>
	}
	/**
	 * 鏄剧ず宸叉敮浠樺垪琛�
	 * @return
	 */
	public StringBuffer getShowPayList() {
		showPayList = new StringBuffer();
		List<Orders> list = DAOFactory.getOrdersDAOInstance().searchPaidList(user);
		for (Orders orders : list) {
			showPayList.append("<tr class=\"tr\"><td class=\"td1\"><span class=\"no\">");
			showPayList.append(orders.getOrder_id());
			showPayList.append("</span></td><td class=\"td1\"><span class=\"food\">");
			showPayList.append(orders.getMenu().getMenu_name());
			showPayList.append("</span></td><td class=\"td1\"><span class=\"number\">");
			showPayList.append(orders.getOrder_num());
			showPayList.append("</span></td><td class=\"td1\"><span class=\"per_price\">");
			showPayList.append(orders.getMenu().getMenu_price());
			showPayList.append("</span></td><td class=\"td1\"><span class=\"price\">");
			showPayList.append(orders.getOrder_num() * orders.getMenu().getMenu_price());
			showPayList.append("</span></td><td class=\"td1\"><span class=\"information\">");
			showPayList.append(orders.getOrder_notice() + "," + orders.getOther_notice());
			showPayList.append("</span></td></tr>");
		}
		return showPayList;
		
//		<tr class="tr">
//        <td class="td1"><span class="no">1</span></td>
//        <td class="td1"><span class="food">鑿滃搧</span></td>
//        <td class="td1"><span class="number">1</span></td>
//        <td class="td1"><span class="per_price">19.0</span></td>
//        <td class="td1"><span class="price">19.0</span></td>
//        <td class="td1"><span class="information">鑿滃搧淇℃伅</span></td>
//    	</tr>
	}
	
	
}
