# blockforge
A cross-platform app that lets you install Minecraft mods and more!
![Blockforge](logo.png)

## Why Blockforge?
1. It can run on anything that has some computing power, and Java!
2. It is very simple. Just click what you want to install, and DONE!

## How to install
![Confused](confused.jpg)

### Wait a sec... How to install Java
Blockforge needs a specific version of Java (which is Java 21).
If you are on Windows, go to https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.exe and run the installer.
Now, open the terminal, and type 
```pwsh
java --version
```
It should say something like Java 21.

If you have a Apple Silicon Mac, go to https://download.oracle.com/java/21/latest/jdk-21_macos-aarch64_bin.dmg and run the installer.
If you have a Intel Mac (old macs sold before ~2020), go to https://download.oracle.com/java/21/latest/jdk-21_macos-x64_bin.dmg and run the installer.
Now, open the terminal, and type 
```bash
java --version
```

If you are on a Fedora-based distro, open the terminal and run
```bash
sudo dnf install java-21-openjdk-devel gradlew
```

If you are on a Debian-based distro, open the terminal and run
```bash
sudo apt install openjdk-21-jdk gradlew -y
```
If you are on a Archlinux-based distro, open the terminal and run
```fish
sudo pacman -S jdk21-openjdk gradlew
```
## How to run

Download the zip file and extract it.
Now, open the folder in the terminal and run
```bash
./gradlew run
```
YOU HAVE SUCCSESSFULLY INSTALLED BLOCKFORGE!
