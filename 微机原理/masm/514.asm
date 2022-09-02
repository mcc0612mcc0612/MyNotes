DATA	SEGMENT
ORG		1000H	
NUM		DB		70,23,42,17,41
Z		DB		?
DATA	ENDS
CODE	SEGMENT
		ASSUME	CS:CODE,DS:DATA
START:	
		MOV		AX,DATA
		MOV		DS,AX
		LEA		SI,NUM 

		MOV		AL,[SI+2]
		CBW
		MOV		BX,AX
		MOV		AL,[SI+3]
		CBW
		MUL		BX
		MOV		BX,AX

		MOV		AL,[SI]
		CBW
		
		MOV		DX,AX
		MOV		AL,[SI+1]
		CBW

		ADD		AX,DX
		SUB		AX,BX
		IDIV	BYTE PTR[SI+4]
		MOV     Z,AL

		MOV		BL,AL
		MOV		CL,4
		SHR		AL,CL
		ADD     AL,30H
        MOV     DL,AL
        MOV     AH,2
        INT     21H
        AND     BL,0FH
        ADD     BL,30H
        MOV     DL,BL
        INT     21H
		; CMP		AL,0AH
	; 	JB		A1
	; 	ADD		AL,07H

	; A1:	ADD		AL,30H
	; 	MOV		DL,AL
	; 	MOV		AH,2
	; 	INT		21H

	; 	MOV		AL,BL
	; 	SHL		AL,CL
	; 	SHR		AL,CL
	; 	CMP		AL,0AH
	; 	JB		A2

	; A2:	ADD		AL,30H
	; 	MOV		DL,AL
	; 	MOV		AH,2
	; 	INT		21H

		MOV		AH,4CH
		INT		21H

CODE	ENDS
		END		START

	










