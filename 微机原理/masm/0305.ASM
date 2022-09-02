DSEG	SEGMENT
		NUM		DB 1000 DUP(?)
DSEG	ENDS
CSEG	SEGMENT
		ASSUME	CS:CSEG,DS:DSEG
START:	MOV		AX,DSEG
		MOV		DS,AX

		MOV     AX,0
        DEC     AX
        ADD     AX,3FFFH
        ADD     AX,AX
        NOT     AX
        SUB     AX,3
        OR      AX,0FBFDH
        AND     AX,0AFCFH
        SHL     AX,1
        RCL     AX,1
        
		MOV 	AH,4CH
		INT		21H
CSEG	ENDS
		END		START