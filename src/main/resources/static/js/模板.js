
new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    // 设置栏数据
    data:{
      sideDrawer: true,
      cards: ['Today'],
      //设置开关
      settingDialog:false,
      tableDialog: false,
      notifications: false,
      sound: true,
      widgets: false,
      //侧边栏数据
      sideDrawerlinks: [
        { icon: 'mdi-microsoft', text: '商品管理', url: '商品管理.html' },
          { icon: 'mdi-cart-minus', text: '商品小类管理', url: '商品小类管理.html' },
        { icon: 'mdi-dog', text: '宠物管理', url: '宠物管理.html' },
        { icon: 'mdi-account-cog', text: '用户管理', url: '用户管理.html' },
        { icon: 'mdi-nintendo-switch', text: '订单管理', url: '订单管理.html' }
      ],
      
    },

    methods:{
      //侧边栏开关
      toggleDrawer() {
        this.sideDrawer = !this.sideDrawer;
      },
      
    }
  })
