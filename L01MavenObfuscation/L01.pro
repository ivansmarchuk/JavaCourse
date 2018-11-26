java -jar proguard\proguard-base-5.3.1.jar @L01.pro

-injars       target/01-MavenObfuscation.jar
-outjars      target/01-MavenObfuscation-out.jar

-libraryjars  <java.home>/lib/rt.jar #contains all of the compiled class files for the base Java Runtime environment
-printmapping pgmapout.map
-dontwarn

-keep public class com.example.L01MavenObfuscation.Main {public static void main(java.lang.String[]);}