# CirclePointViewPager
一个自带小圆点的ViewPger，ViewPager可以跟着手势滑动而滑动

在项目 build.gradle 增加

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
在app的build.gradle下增加

	dependencies {
	        compile 'com.github.mochen1995:CirclePointViewPager:v1.0'
	}
