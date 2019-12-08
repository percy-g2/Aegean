cmake_minimum_required(VERSION 3.4.1)

add_library(aegean-jni SHARED
        aegean-jni.c)

# Include libraries needed for mimba-jni lib
target_link_libraries(aegean-jni
                      android
log)
