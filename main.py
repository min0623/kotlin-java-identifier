import argparse
import subprocess
import os

def evosuite_test(java_file, kotlin_file):
    java_class_name, kotlin_class_name = java_file.split('/')[-1].split('.')[0], kotlin_file.split('/')[-1].split('.')[0]
    if java_class_name != kotlin_class_name or java_file.split('/')[-1].split('.')[1] != 'java' or kotlin_file.split('/')[-1].split('.')[1] != 'kt':
        raise NameError('File Name Error!')
    class_name = java_class_name

    default_libs = [
        'lib/evosuite-standalone-runtime-1.1.0.jar',
        'lib/junit-4.12.jar',
        'lib/hamcrest-core-1.3.jar',
    ]
    case_specific_libs = [
        'lib/commons-net-3.8.0.jar',
    ]
    kotlin_libs = [
        'lib/kotlin-stdlib.jar',
        'lib/kotlin-stdlib-jdk8.jar',
        'lib/kotlin-reflect.jar',
        'lib/kotlin-test.jar',
    ]

    classpath = 'evosuite-tests'
    classpath += ':' + ':'.join(default_libs)
    classpath += ':' + ':'.join(case_specific_libs)
    classpath += ':' + ':'.join(kotlin_libs)

    if not os.path.isfile(java_file):
        raise FileNotFoundError("Java file does not exist.")
    if not os.path.isfile(kotlin_file):
        raise FileNotFoundError("Kotlin file does not exist.")
    if os.path.isfile('java_classpath/' + class_name + '.class'):
        os.remove('java_classpath/' + class_name + '.class')
    if os.path.isfile('kotlin_classpath/' + class_name + '.class'):
        os.remove('kotlin_classpath/' + class_name + '.class')

    subprocess.run(['javac', java_file, '-d', 'java_classpath', '-cp', ':'.join(case_specific_libs)], capture_output=True, text=True)
    subprocess.run(['kotlinc', kotlin_file, '-jvm-target', '1.8', '-d', 'kotlin_classpath', '-cp', ':'.join(case_specific_libs)], capture_output=True, text=True)
    if not os.path.isfile('java_classpath/' + class_name + '.class'):
        raise FileNotFoundError("Java file cannot be compiled.")
    if not os.path.isfile('kotlin_classpath/' + class_name + '.class'):
        raise FileNotFoundError("Kotlin file cannot be compiled.")

    subprocess.run(['java', '-jar', 'lib/evosuite-1.1.0.jar', '-class', class_name, '-projectCP', 'java_classpath' + ':' + ':'.join(case_specific_libs)])
    subprocess.run(['javac', 'evosuite-tests/' + class_name + '_ESTest.java', '-classpath', 'java_classpath:' + classpath])
    java_run = subprocess.run(['java', '-cp', 'java_classpath:' + classpath, 'org.junit.runner.JUnitCore', class_name + '_ESTest'], capture_output=True, text=True)
    kotlin_run = subprocess.run(['java', '-cp', 'kotlin_classpath:' + classpath, 'org.junit.runner.JUnitCore', class_name + '_ESTest'], capture_output=True, text=True)
    java_run_stdout, kotlin_run_stdout = [l for l in java_run.stdout.split('\n') if len(l) != 0], [l for l in kotlin_run.stdout.split('\n') if len(l) != 0]
    return java_run_stdout, kotlin_run_stdout



if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Kotlin-Java Identifier')
    parser.add_argument('-j', '--java', type=str, required=True)
    parser.add_argument('-k', '--kotlin', type=str, required=True)
    parser.add_argument('-a', '--all', action='store_true')

    args = parser.parse_args()

    if args.all:
        java_dir = args.java
        kotlin_dir = args.kotlin
        try:
            if not os.path.isdir(java_dir):
                raise FileNotFoundError('Java directory does not exist.')
            if not os.path.isdir(kotlin_dir):
                raise FileNotFoundError('Kotlin directory does not exist.')
            result = dict()
            java_files = [java_dir + '/' + file for file in os.listdir(java_dir) if file.endswith(".java")]
            for java_file in java_files:
                class_name = java_file.split('/')[-1].split('.')[0]
                try:
                    kotlin_file = kotlin_dir + '/' + class_name + '.kt'
                    java_run_stdout, kotlin_run_stdout = evosuite_test(java_file, kotlin_file)
                    if not 'OK' in java_run_stdout[-1]:
                        raise NameError('Test Generation Error!')
                    else:
                        n_tests = int(''.join([d for d in java_run_stdout[-1] if d.isdigit()]))
                    if 'OK' in kotlin_run_stdout[-1]:
                        result[class_name] = str(n_tests) + '/' + str(n_tests) + ' passed.'
                    else:
                        n_failed = int(''.join([d for d in kotlin_run_stdout[-1].split(',')[1] if d.isdigit()]))
                        class_test = '(' + class_name + '_ESTest)'
                        failed_tests = sorted(['test' + str(line.split(class_test)[0].split('test')[1]) for line in kotlin_run_stdout if class_test in line])
                        failed_out = '(' + ', '.join(failed_tests) + ')'
                        result[class_name] = str(n_tests - n_failed) + '/' + str(n_tests) + ' passed, ' + str(n_failed) +  '/' + str(n_tests) + ' failed. ' + failed_out
                except Exception as ex:
                    result[class_name] = ex
            max_len_name = max([len(class_name) for class_name in result])
            print()
            for class_name in result:
                print(class_name.rjust(max_len_name, ' ') + ':', result[class_name])
        except Exception as e:
            print(e)
    else:
        try:
            java_file = args.java
            kotlin_file = args.kotlin
            java_run_stdout, kotlin_run_stdout = evosuite_test(java_file, kotlin_file)
            class_name = java_file.split('/')[-1].split('.')[0]
            if not 'OK' in java_run_stdout[-1]:
                    raise NameError('Test Generation Error!')
            else:
                n_tests = int(''.join([d for d in java_run_stdout[-1] if d.isdigit()]))
            if 'OK' in kotlin_run_stdout[-1]:
                print('\nAll', n_tests, 'tests passed for kotlin class file.')
            else:
                n_failed = int(''.join([d for d in kotlin_run_stdout[-1].split(',')[1] if d.isdigit()]))
                class_test = '(' + class_name + '_ESTest)'
                failed_tests = sorted(['test' + str(line.split(class_test)[0].split('test')[1]) for line in kotlin_run_stdout if class_test in line])
                failed_out = '(' + ', '.join(failed_tests) + ')'
                print('\n' + str(n_failed), 'of', n_tests, 'tests', failed_out, 'failed for kotlin class file.', end='\n\n')
                with open('evosuite-tests/' + class_name + '_ESTest.java', 'r') as t:
                    test_file = t.read()
                tests = test_file.split('@Test')
                for test in tests[1:]:
                    for failed_test in failed_tests:
                        if failed_test in test:
                            failed_tests.remove(failed_test)
                            test_lines = test.split('\n')
                            while failed_test not in test_lines[0]:
                                test_lines = test_lines[1:]
                            while '  }' not in test_lines[-1]:
                                test_lines = test_lines[:-1]
                            print('\n'.join(test_lines), end='\n\n')
                            break
        except Exception as e:
            print(e)




