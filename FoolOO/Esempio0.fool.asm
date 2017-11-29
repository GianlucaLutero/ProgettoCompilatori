push 0
push 5
push 2
add
push function0
lfp
push 8
push -2
lfp
add
lw
lfp
push -3
lfp
add
lw
js
push 1
beq label2
push -2
lfp
add
lw
b label3
label2:
push 1
label3:
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
