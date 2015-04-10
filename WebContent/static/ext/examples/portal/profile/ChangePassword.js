/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
//修改密码
Ext.define('Forestry.app.profile.ChangePassword', {
	style : 'padding:5px;',
	extend : 'Ext.panel.Panel',
	initComponent : function() {
		var newPasswordPart = Ext.create('Ext.form.field.Text', {
			id : 'newPassword',
			name : 'newPassword',
			fieldLabel : '新密码',
			inputType : 'password',
			labelWidth : 75,
			width : 280,
			allowBlank : false
		});

		Ext.apply(this, {
			items : [ {
				id : 'oldPassword',
				name : 'oldPassword',
				xtype : 'textfield',
				inputType : 'password',
				fieldLabel : '原密码',
				labelWidth : 75,
				width : 280,
				allowBlank : false
			}, newPasswordPart, {
				id : 'confirmPassword',
				name : 'confirmPassword',
				xtype : 'textfield',
				inputType : 'password',
				vtype : 'confirmpwd',
				firstPassField : newPasswordPart,
				fieldLabel : '重复新密码',
				labelWidth : 75,
				width : 280,
				allowBlank : false
			} ],
			buttons : [ '->', {
				text : '确定',
				iconCls : 'icon-accept',
				handler : function() {
					if ("" == Ext.getCmp('newPassword').getValue() || "" == Ext.getCmp('oldPassword').getValue() || "" == Ext.getCmp('confirmPassword').getValue()) {
						Ext.MessageBox.show({
							title : '系统信息',
							msg : '必填项不能为空！',
							buttons : Ext.MessageBox.YES,
							icon : Ext.MessageBox.WARNING
						});
						return;
					}
					if (Ext.getCmp('newPassword').getValue() != Ext.getCmp('confirmPassword').getValue()) {
						Ext.MessageBox.show({
							title : '系统信息',
							msg : '新密码和重复新密码要相同！',
							buttons : Ext.MessageBox.YES,
							icon : Ext.MessageBox.WARNING
						});
						return;
					}
					Ext.Ajax.request({
						url : appBaseUri + '/sys/sysuser/resetPassword',
						params : {
							newPassword : Ext.getCmp('newPassword').value,
							oldPassword : Ext.getCmp('oldPassword').value,
							userName : userName
						},
						method : "POST",
						success : function(response) {
							var ret = eval("(" + response.responseText + ")");
							if (ret.result == 1) {
								Ext.MessageBox.show({
									title : '系统信息',
									msg : '更新密码成功！',
									buttons : Ext.MessageBox.YES,
									icon : Ext.MessageBox.INFO,
									fn : function() {
										Ext.getCmp("profile.ChangePassword").close();
									}
								});
							} else if (ret.result == -2) {
								Ext.MessageBox.show({
									title : '系统信息',
									msg : '请输入正确原密码！',
									buttons : Ext.MessageBox.YES,
									icon : Ext.MessageBox.WARNING
								});
							}
						},
						failure : function(response) {
							Ext.MessageBox.show({
								title : '系统信息',
								msg : '系统出错，更新密码失败！',
								buttons : Ext.MessageBox.YES,
								icon : Ext.MessageBox.ERROR
							});
						}
					});
				}
			}, {
				scope : this,
				iconCls : 'icon-cancel',
				text : '取消',
				handler : function() {
					Ext.getCmp("profile.ChangePassword").close();
				}
			}, '->' ]
		});
		this.callParent(arguments);
	}
});
