
# 使用的最小版本
cmake_minimum_required(VERSION 3.22.1)

# name
project("revdemo")

# 添加库  加载的时候 加载的是 native-lib
add_library(${CMAKE_PROJECT_NAME} SHARED
        # List C/C++ source files with relative paths to this CMakeLists.txt.

        native-lib.cpp)


add_library(khsig
        SHARED
        # List C/C++ source files with relative paths to this CMakeLists.txt.
        md5.cpp
        base_enc.cpp)
# Specifies libraries CMake should link to your target library. You
# can link libraries from various origins, such as libraries defined in this
# build script, prebuilt third-party libraries, or Android system libraries.
target_link_libraries(${CMAKE_PROJECT_NAME}
        khsig
        # List libraries link to the target library
        android
        log)


target_link_libraries(khsig
        # List libraries link to the target library
        android
        log)

