import subprocess


def proces(command):
    commands = command.split("|")
    subCommands1 = filter(lambda x : x != "", commands[0].split())
    ps = subprocess.Popen(tuple(subCommands1), stdout=subprocess.PIPE)
    for i in commands[1::]:
        i = filter(lambda x : x != "", i.split())
        print(subprocess.check_output(tuple(i), stdin=ps.stdout))
        ps.wait()


def main():
    command = "ls -l | grep main.py | more"
    proces(command)

if __name__ == '__main__':
    main()