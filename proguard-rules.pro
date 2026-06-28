-keep class com.afm.shopaccounts.data.entity.** { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
