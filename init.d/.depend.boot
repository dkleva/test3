TARGETS = console-setup resolvconf mountkernfs.sh ufw plymouth-log apparmor screen-cleanup hostname.sh udev keyboard-setup cryptdisks cryptdisks-early networking iscsid hwclock.sh mountdevsubfs.sh checkroot.sh lvm2 open-iscsi urandom checkfs.sh bootmisc.sh mountnfs-bootclean.sh mountnfs.sh mountall.sh checkroot-bootclean.sh kmod procps mountall-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
networking: resolvconf mountkernfs.sh urandom procps
iscsid: networking
hwclock.sh: mountdevsubfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
lvm2: cryptdisks-early mountdevsubfs.sh udev
open-iscsi: networking iscsid
urandom: hwclock.sh
checkfs.sh: cryptdisks lvm2 checkroot.sh
bootmisc.sh: udev mountnfs-bootclean.sh checkroot-bootclean.sh mountall-bootclean.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
kmod: checkroot.sh
procps: mountkernfs.sh udev
mountall-bootclean.sh: mountall.sh
