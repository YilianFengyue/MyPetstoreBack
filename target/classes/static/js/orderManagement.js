
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
    //订单卡片
    selected: [0,1,2],
    orderItems: [
      {
        orderid: 1000,
        userid: "ACID",
        orderdate: "2024-11-04T16:00:00.000+00:00",
        shipaddr1: "901 San Antonio Road",
        shipaddr2: "MS UCUP02-206",
        shipcity: "Palo Alto",
        shipstate: "CA",
        shipzip: "94303",
        shipcountry: "USA",
        billaddr1: "901 San Antonio Road",
        billaddr2: "MS UCUP02-206",
        billcity: "Palo Alto",
        billstate: "CA",
        billzip: "94303",
        billcountry: "USA",
        courier: "USA",
        totalprice: 193.50,
        billtofirstname: "ABC",
        billtolastname: "XYX",
        shiptofirstname: "ABC",
        shiptolastname: "XYX",
        creditcard: "999 9999 9999 9999",
        exprdate: "12/03",
        cardtype: "Visa",
        locale: "CA"
      },
      {
        orderid: 1002,
        userid: "ACID",
        orderdate: "2024-11-04T16:00:00.000+00:00",
        shipaddr1: "901 San Antonio Road",
        shipaddr2: "MS UCUP02-206",
        shipcity: "Palo Alto",
        shipstate: "CA",
        shipzip: "94303",
        shipcountry: "USA",
        billaddr1: "901 San Antonio Road",
        billaddr2: "MS UCUP02-206",
        billcity: "Palo Alto",
        billstate: "CA",
        billzip: "94303",
        billcountry: "USA",
        courier: "USA",
        totalprice: 193.50,
        billtofirstname: "ABC",
        billtolastname: "XYX",
        shiptofirstname: "ABC",
        shiptolastname: "XYX",
        creditcard: "999 9999 9999 9999",
        exprdate: "12/03",
        cardtype: "Visa",
        locale: "CA"
      },
      {
        orderid: 1003,
        userid: "ACID",
        orderdate: "2024-11-04T16:00:00.000+00:00",
        shipaddr1: "901 San Antonio Road",
        shipaddr2: "MS UCUP02-206",
        shipcity: "Palo Alto",
        shipstate: "CA",
        shipzip: "94303",
        shipcountry: "USA",
        billaddr1: "901 San Antonio Road",
        billaddr2: "MS UCUP02-206",
        billcity: "Palo Alto",
        billstate: "CA",
        billzip: "94303",
        billcountry: "USA",
        courier: "USA",
        totalprice: 193.50,
        billtofirstname: "ABC",
        billtolastname: "XYX",
        shiptofirstname: "ABC",
        shiptolastname: "XYX",
        creditcard: "999 9999 9999 9999",
        exprdate: "12/03",
        cardtype: "Visa",
        locale: "CA"
      },
      {
        orderid: 1003,
        userid: "ACID",
        orderdate: "2024-11-04T16:00:00.000+00:00",
        shipaddr1: "901 San Antonio Road",
        shipaddr2: "MS UCUP02-206",
        shipcity: "Palo Alto",
        shipstate: "CA",
        shipzip: "94303",
        shipcountry: "USA",
        billaddr1: "901 San Antonio Road",
        billaddr2: "MS UCUP02-206",
        billcity: "Palo Alto",
        billstate: "CA",
        billzip: "94303",
        billcountry: "USA",
        courier: "USA",
        totalprice: 193.50,
        billtofirstname: "ABC",
        billtolastname: "XYX",
        shiptofirstname: "ABC",
        shiptolastname: "XYX",
        creditcard: "999 9999 9999 9999",
        exprdate: "12/03",
        cardtype: "Visa",
        locale: "CA"
      },
    ],
    //订单详情
    detailDialog:false,
    editedItem: {
      userid: '',
      password: 0,
      email: 0,
      lastname: 0,
      firstname: 0,
    },
    defaultItem: {
      userid: '',
      password: 0,
      email: 0,
      lastname: 0,
      firstname: 0,
    },
    //delete
    dialogDelete:false,
    //发货消息条
    snackbar: false,
    text: `已发货！`,
  },
  computed: {
    // 计算选中的订单名称
    selectedNames() {
      return this.selected.map(index => this.orderItems[index].orderid);
    }
  },
  watch: {
    detialDialog (val) {
      val || this.close()
    },
    dialogDelete (val) {
      val || this.closeDelete()
    },
  },
  created () {
    this.initialize()
  },
  methods:{
    //侧边栏开关
    toggleDrawer() {
      this.sideDrawer = !this.sideDrawer;
    },
    initialize () {
      this.getAll();

    },
    getAll(){
      //发送ajax请求
      axios.get("/orders").then((res)=>{
        this.orderItems = res.data.data;

      });
    },


    //详情对话框
    close () {
      this.detailDialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    editItem (item) {
      this.detailDialog = true
      console.log("yes")
      this.editedIndex = this.orderItems.indexOf(item)
      this.editedItem = Object.assign({}, item)

    },
    save () {
      if (this.editedIndex > -1) {
        //1.编辑操作
        // Object.assign(this.Accounts[this.editedIndex], this.editedItem)
        //发送ajax请求
        console.log(this.editedItem)
        axios.put("/orders",this.editedItem).then((res)=>{

        }).finally(()=>{
          this.getAll();
        });
      } else {
        //2.新增操作
        this.orderItems.push(this.editedItem)
      }
      this.close()
    },
    //删除
    deleteItem () {
      // this.editedIndex = this.Accounts.indexOf(item)
      // this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm () {

      if (this.selectedNames.length === 0) {
        this.text="请先选择订单！";
        this.snackbar=true;
        return;
      }
      try {
        const response =  axios.post('/orders/delete', { orderIds: this.selectedNames });

      } catch (error) {
        console.error(error);
      }finally {
        this.closeDelete();
        this.text="已删除！";
        this.snackbar=true;
        this.getAll();
      }

    },
    closeDelete () {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    // 批量发货
    async batchShipOrders() {
      if (this.selectedNames.length === 0) {
        this.text="请先选择订单！";
        this.snackbar=true;
        return;
      }
      try {
        const response = await axios.post('/orders/delete', { orderIds: this.selectedNames });

      } catch (error) {
        console.error(error);
      }finally{
        this.text="已发货！";
        this.snackbar=true;
        this.getAll();
      }
    },
  }
})
