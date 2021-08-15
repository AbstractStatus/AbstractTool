# AbstractTool
抽象工具


*注意：每一次新建工具：
1、必须使用新建kotlin-class，类名为ToolxxxxxInfo，编写继承ToolInfo的静态方法
2、新建任意语言的Activity，类名为ToolxxxxxMainActivity，继承BaseActivity，layout文件需要加入toolBar，如果使用到第三方库，请于对应的Info类重写getMap方法，Key值在HashMapKey类中。
3、ToolList添加上述两个类
4、资源文件和id需要使用toolxxxxx的前缀