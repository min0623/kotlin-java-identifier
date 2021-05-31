import argparse
import subprocess

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Kotlin-Java Identifier')
    parser.add_argument('-j', '--java', type=str, required=True)
    parser.add_argument('-k', '--kotlin', type=str, required=True)
    
    args = parser.parse_args()

    java_file = args.java
    kotlin_file = args.kotlin

    class_name = java_file.split('/')[-1].split('.')[0]
    classpath = 'evosuite-tests:lib/evosuite-standalone-runtime-1.1.0.jar:lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar'
    kotlin_libs = [
        'lib/kotlin-stdlib.jar',
        'lib/kotlin-stdlib-jdk8.jar',
        'lib/kotlin-reflect.jar',
        'lib/kotlin-test.jar',
    ]

    classpath += ':' + ':'.join(kotlin_libs)

    subprocess.run(['javac', java_file, '-d', 'java_classpath'])
    subprocess.run(['kotlinc', kotlin_file, '-jvm-target', '1.8', '-d', 'kotlin_classpath'])
    subprocess.run(['java', '-jar', 'lib/evosuite-1.1.0.jar', '-class', class_name, '-projectCP', 'java_classpath'])
    subprocess.run(['javac', 'evosuite-tests/' + class_name + '_ESTest.java', '-classpath', 'java_classpath:' + classpath])
    subprocess.run(['java', '-cp', 'java_classpath:' + classpath, 'org.junit.runner.JUnitCore', class_name + '_ESTest'])
    subprocess.run(['java', '-cp', 'kotlin_classpath:' + classpath, 'org.junit.runner.JUnitCore', class_name + '_ESTest'])
