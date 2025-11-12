
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

    //表格数据
    itemsPerPageArray: [4, 8, 12],
    search: '',
    filter: {},
    sortDesc: false,
    page: 1,
    itemsPerPage: 4,
    sortBy: 'name',
    keys: [
      'catid',
      'name',
      'descn',

    ],
    Categories: [
      {
        catid: "BIRDS",
        name: "Birds",
        descn: "<image src=\"../images/birds_icon.gif\"><font size=\"5\" color=\"blue\"> Birds</font>"
      },
      {
        catid: "CATS",
        name: "Cats",
        descn: "<image src=\"../images/cats_icon.gif\"><font size=\"5\" color=\"blue\"> Cats</font>"
      },
      {
        catid: "DOGS",
        name: "Dogs",
        descn: "<image src=\"../images/dogs_icon.gif\"><font size=\"5\" color=\"blue\"> Dogs</font>"
      },
      {
        catid: "FISH",
        name: "Fish",
        descn: "<image src=\"../images/fish_icon.gif\"><font size=\"5\" color=\"blue\"> Fish</font>"
      },

    ],
    //对话框
    dialogDelete:false,
    dialog:false,
    editedItem: {
      catid: "",
      name: "",
      descn: ""
    },
    editedIndex: -1, // 记录编辑的索引

  },
  //初始化
  created () {
    this.initialize()
  },

  //category
  computed: {
    numberOfPages () {
      return Math.ceil(this.Categories.length / this.itemsPerPage)
    },
    filteredKeys () {
      return this.keys.filter(key => key !== 'Name')
    },
    formTitle () {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    },
  },
  methods:{
    //侧边栏开关
    toggleDrawer() {
      this.sideDrawer = !this.sideDrawer;
    },
    initialize () {
      this.getAll();
    },
    //查询所有
    getAll(){
      //发送ajax请求
      axios.get("/category").then((res)=>{
          this.Categories = res.data.data;

      });
    },

    nextPage () {
      if (this.page + 1 <= this.numberOfPages) this.page += 1
    },
    formerPage () {
      if (this.page - 1 >= 1) this.page -= 1
    },
    updateItemsPerPage (number) {
      this.itemsPerPage = number
    },
    //delete
    deleteItem (item) {
      this.editedIndex = this.Categories.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },
    closeDelete () {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    deleteItemConfirm () {
      // this.Accounts.splice(this.editedIndex, 1)
      // this.closeDelete()
      const catid = this.editedItem.catid; // 获取要删除的用户 ID
      console.log(catid)
      axios.delete(`/category/${catid}`)
          .then((res) => {
            console.log("删除成功:", res.data);
            alert("用户删除成功！");

            // 在前端同步删除
            this.Categories.splice(this.editedIndex, 1);
          })
          .catch((err) => {
            console.error("删除失败:", err);
            alert("删除失败，请稍后再试！");
          })
          .finally(() => {
            this.closeDelete();
          });


    },
    //编辑对话框
    editItem (item) {
      this.editedIndex = this.Categories.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },
    save () {
      if (this.editedIndex > -1) {
        //1.编辑操作
        // Object.assign(this.Accounts[this.editedIndex], this.editedItem)
        //发送ajax请求
        console.log(this.editedItem)
        axios.put("/category",this.editedItem).then((res)=>{

        }).finally(()=>{
          this.getAll();
        });
      } else {
        //2.新增操作
        axios.post("/category",this.editedItem).then((res)=>{

        }).finally(()=>{
          this.getAll();
        })
        this.Categories.push(this.editedItem)
      };
      this.close()
    },
    close () {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
  }
})
