package com.my.bookmarker.training;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

import java.util.List;

public class KomoranTraining {
    public static void main(String[] args) {
        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        String strToAnalyze = "잊지 말아요\r\n"
        		+ "나를 두고 가면 어떡하나요 그대\r\n"
        		+ "그대라는 사람은\r\n"
        		+ "내겐 가장 소중한 사람\r\n"
        		+ "그대랍니다\r\n"
        		+ "태어나 처음으로\r\n"
        		+ "가슴 떨리던 사람\r\n"
        		+ "수천 번을 보아도\r\n"
        		+ "내게 하나뿐인 그런 사람\r\n"
        		+ "슬픈 사랑이라고 말하지 말아요\r\n"
        		+ "다시 생각해보면 행복한 나인데\r\n"
        		+ "우리 사랑하는 만큼\r\n"
        		+ "서로 아파하지만\r\n"
        		+ "그냥 그냥 그냥 이대로\r\n"
        		+ "사랑하자\r\n"
        		+ "바보랍니다\r\n"
        		+ "그저 한 사람만\r\n"
        		+ "바라만 보는 사랑\r\n"
        		+ "혹시 보고플 때면\r\n"
        		+ "그땐 그땐 그냥 내게 와요\r\n"
        		+ "슬픈 사랑이라고 말하지 말아요\r\n"
        		+ "다시 생각해보면 행복한 나인데\r\n"
        		+ "우리 사랑하는 만큼\r\n"
        		+ "서로 아파하지만\r\n"
        		+ "그냥 그냥 그냥 이대로\r\n"
        		+ "사랑하자\r\n"
        		+ "그댈 볼 수 없단 건\r\n"
        		+ "죽음보다 더 내게\r\n"
        		+ "가장 무서운 일인데\r\n"
        		+ "나의 곁에 있어요\r\n"
        		+ "떠나가지 말아요\r\n"
        		+ "나의 사랑입니다\r\n"
        		+ "평생을 바쳐도 아깝지 않을 사람\r\n"
        		+ "그대일 테니까\r\n"
        		+ "우리 사랑하는 만큼\r\n"
        		+ "서로 아파하지만\r\n"
        		+ "그냥 그냥 그냥 이대로 사랑하자";

        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze);

//        System.out.println(analyzeResultList.getPlainText());

        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
        	if (token.getPos().contains("NN")) System.out.format("%s / %s\n", token.getMorph(), token.getPos());
        }

    }}
