DATA	SEGMENT
ORG     1000H
NUM     DB      70,23,42,17,41
Z       DB      ?
DATA    ENDS
CODE    SEGMENT
        ASSUME  CS:CODE,DS:DATA
START:  MOV     AX,DATA
        MOV     DS,AX
        LEA     SI,NUM
        ;以上为第一步
        MOV     AL,[SI+2]
        MUL     BYTE PTR[SI+3]
        MOV     BX,AX
        ;以上为第二步
        MOV     AX,0
        MOV     AL,[SI]
        ADD     AL,[SI+1]
        SUB     AX,BX
        CWD
        ;以上为第三步
        MOV     BL,[SI+4]
        DIV     BL;AX/BL商送AL，余数送AH
        MOV     Z,AL
        ;以上为第四步
        MOV     BL,AL
        MOV     CL,4
        SHR     AL,CL
        ADD     AL,30H
        MOV     DL,AL
        MOV     AH,2
        INT     21H
        AND     BL,0FH
        ADD     BL,30H
        MOV     DL,BL
        INT     21H
        ;以上为第五步
        MOV     AH,4CH
        INT     21H
CODE    ENDS
        END    START