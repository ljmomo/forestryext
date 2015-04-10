/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
// 林区光照度变化表
Ext.require([ 'Ext.chart.*', 'Ext.layout.container.Fit', 'Ext.window.MessageBox', 'Ext.grid.Panel' ]);

Ext.define('Forestry.app.report.LightfallReport', {
	extend : 'Ext.panel.Panel',
	initComponent : function() {

		var store1 = Ext.create('Ext.data.JsonStore', {
			fields : [ 'name', 'data1', 'mx', 'mn', 'st', 'ed' ],
			autoLoad : true,
			proxy : {
				type : 'ajax',
				url : appBaseUri + '/sys/sensordata/getSensorDataStatistics?sensorType=3',
				reader : {
					type : 'array',
					root : ''
				}
			}
		})

		var chart = Ext.create('Ext.chart.Chart', {
			animate : true,
			shadow : true,
			store : store1,
			axes : [ {
				type : 'Numeric',
				position : 'left',
				fields : [ 'data1' ],
				title : 'lx',
				grid : true
			}, {
				type : 'Category',
				position : 'bottom',
				fields : [ 'name' ]
			} ],
			series : [ {
				type : 'line',
				axis : 'left',
				gutter : 80,
				xField : 'name',
				yField : [ 'data1' ],
				tips : {
					trackMouse : true,
					width : 150,
					height : 110,
					layout : 'fit',
					renderer : function(klass, item) {
						var storeItem = item.storeItem, data = [ {
							name : 'data1',
							data : storeItem.get('data1')
						} ], i, l, html;

						this.setTitle("时刻：" + storeItem.get('name') + "<br/>平均光照度：" + storeItem.get('data1') + "lx" + "<br/>最高光照度：" + storeItem.get('mx') + "lx" + "<br/>最低光照度：" + storeItem.get('mn') + "lx"
								+ "<br/>开始光照度：" + storeItem.get('st') + "lx" + "<br/>结束光照度：" + storeItem.get('ed') + "lx");
					}
				}
			} ]
		});

		var panel1 = Ext.create('widget.panel', {
			layout : 'fit',
			items : chart
		});

		Ext.apply(this, {
			layout : 'fit',
			region : "center",
			items : [ panel1 ]
		});

		this.callParent(arguments);
	}
});