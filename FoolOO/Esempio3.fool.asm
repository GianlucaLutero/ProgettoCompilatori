push 0
push function2
lfp
push 3
lfp
push -2
lfp
add
lw
js
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

function1:
cfp
lra
push 1
lfp
add
lw
push 1
add
srv
sra
pop
pop
sfp
lrv
lra
js

function2:
cfp
lra
push function1
push 1
lfp
add
lw
push 0
beq label8
push 0
b label9
label8:
push 1
label9:
push 1
beq label6
push 1
lfp
add
lw
lfp
push 1
lfp
add
lw
push 1
sub
lfp
push -2
lfp
add
lw
js
mult
b label7
label6:
push 1
label7:
srv
pop
sra
pop
pop
sfp
lrv
lra
js
