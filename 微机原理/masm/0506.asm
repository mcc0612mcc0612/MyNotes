DSEG    SEGMENT
STRING DB 'Hello,World!',0DH,0AH,'$'
DSEG    ENDS
CSEG    SEGMENT
        ASSUME  CS:CSEG,DS:DSEG
START:  MOV     AX,DSEG
        MOV     DS,AX
        LEA     DX,STRING
        MOV     AH,09H
        INT     21H
        
        MOV     AH,4CH
        INT     21H
CSEG    ENDS
        END    START