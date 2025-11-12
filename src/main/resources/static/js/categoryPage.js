
new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    // 设置栏数据
    data:{
        drawer: false,
        cards: ['Today'],
        //设置开关
        settingDialog:false,
        tableDialog: false,
        //动态选项卡
        tabs: null,
        text: '1',
        //标签页
        tab: null,
        notifications: false,
        sound: true,
        widgets: false,
        drawer: null,
        links: [
            ['mdi-inbox-arrow-down', '商品管理'],
            ['mdi-send', '用户管理'],
            ['mdi-delete', '订单管理'],
        ],
        singleExpand: false,
        //Category数据
        categories:[],

    },

    created () {
        this.initialize()
    },

    methods:{
        toggleDrawer() {
            this.drawer = !this.drawer;
        },
        //表格管理
        //初始化，调用getall()
        initialize () {
            this.getAll();
            console.log(categories);
        },

        getAll(){
            //发送ajax请求
            axios.get("/category").then((res)=>{
                this.categories = res.data.data;

            });
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
