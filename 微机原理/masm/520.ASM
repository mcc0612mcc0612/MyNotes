DISP_STR    MACRO X ;宏定义.
    MOV     DX, OFFSET X
    MOV     AH, 09H
    INT     21H
ENDM
;----------------------
DSEG   SEGMENT ;数据段.
MES  DB     'Input a decimal number(10~99):',0AH,0DH, '$'
MES1  DB     0AH,0DH,'Show decimal number as hex: $'
x    DW ? ;存放新输入数据.
DSEG ENDS
;----------------------
CSEG SEGMENT ;代码段.
     ASSUME     CS: CSEG, DS: DSEG
START:
    MOV AX, DSEG
    MOV DS, AX
;--------------------------------
TIP:
    DISP_STR MES ;宏调用，提示 Please Input :
;--------------------------------
    MOV x, 0 ;数据清零.
INX:
    MOV AH, 1 ;输入字符.
    INT 21H
;--------------------------------
    CMP AL, 13 ;回车?
    JE IN_END ;是则结束输入.
    CMP AL, '0'
    JB TIP ;小于'0'，不是数字.
    CMP AL, '9'
    JA TIP ;大于'9'，不是数字.
    SUB AL, '0'
    MOV CL, AL
    MOV CH, 0
    MOV AX, x
    MOV BX, 10 ;老数据乘以10
    MUL BX
    ADD AX, CX ;加上新数据.
    MOV x, AX ;保存.
    CMP AX, 99
    JA TIP
    JMP INX
;--------------------------------
IN_END:
    DISP_STR MES1 ;宏调用，提示 The HEX is :
    MOV AX, x
    MOV BX, 16
    MOV CX, 0
JD1:MOV DX, 0
    DIV BX
    ADD DL, '0'
    CMP DL, 3AH
    JB ZZZ
    ADD DL, 7
ZZZ:
    PUSH DX
    INC CX
    CMP AX, 0
    JNE JD1
    MOV AH, 2
JD2:POP DX
    INT 21H
    LOOP JD2
;--------------------------------
    MOV AH, 4CH
    INT 21H
;--------------------------------
CSEG ENDS
    END START