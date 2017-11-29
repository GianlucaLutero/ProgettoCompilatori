push 0
push 3
push -2
lfp
add
lw
push 2
add
print
halt

function0:
cfp
lra
push 2
lfp
add
lw
push -2
lfp
add
lw
push -2
lfp
lw
add
lw
beq label0
push 0
b label1
label0:
push 1
label1:
srv
pop
sra
pop
pop
pop
sfp
lrv
lra
js
