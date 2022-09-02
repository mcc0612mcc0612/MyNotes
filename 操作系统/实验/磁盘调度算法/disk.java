import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class disk {
    public static void main(String[] args) {
        System.out.println(">��ӭʹ�ô��̵���ʵ�����");
        System.out.println(">���̷�������Ϊ��"+TracksToString());
        System.out.println(">��дͷ��ʼλ��Ϊ��"+tracks.get(0));
        System.out.println(">FCFS�����ȷ����㷨");
        FCFS();
        System.out.println("    �ߵ�˳��Ϊ��"+MessageToString());
        System.out.println("    ��ͷ�߹����ܵ�����"+getTracksNum());
        System.out.println(">SSTF���Ѱ��ʱ�������㷨" );
        SSTF();
        System.out.println("    �ߵ�˳��Ϊ��"+MessageToString());
        System.out.println("    ��ͷ�߹����ܵ�����"+getTracksNum());
        System.out.println(">SCANɨ���㷨");
        System.out.println("  *��ͷ��ŵ��ű�С�ķ����ƶ���");
        SCAN(0);
        System.out.println("    �ߵ�˳��Ϊ��"+MessageToString());
        System.out.println("    ��ͷ�߹����ܵ�����"+getTracksNum());
        System.out.println("  *��ͷ��ŵ��ű��ķ����ƶ���");
        SCAN(1);
        System.out.println("    �ߵ�˳��Ϊ��"+MessageToString());
        System.out.println("    ��ͷ�߹����ܵ�����"+getTracksNum());

    }


    private static ArrayList<Integer> tracks;
    private static ArrayList<Integer> trackPath;
    private static int tracksNum;

    static  {
        tracksNum = 0;
        tracks = new ArrayList<>();

        readTrackList();
    }

    private static void readTrackList() {
        try {
            int index = 0;
            ArrayList<String> track = new ArrayList<>();
            InputStream is = new FileInputStream("TrackList");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Scanner sc = new Scanner(reader);
            while(sc.hasNext()){
                track.add(sc.nextLine());
                index ++;
            }

            for (int i = 0;i < index;i ++) {
                String[] words = track.get(i).split(" ");
                for(String item: words)
                    tracks.add(Integer.parseInt(item));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FCFS(){
        tracksNum = 0;
        trackPath = new ArrayList<>();
        trackPath.addAll(tracks);
        for (int i = 1; i < trackPath.size(); i++) {
            tracksNum += Math.abs(trackPath.get(i) - trackPath.get(i-1));
        }

    }

    public static void SSTF(){
        tracksNum = 0;
        trackPath = new ArrayList<>();
        ArrayList<Integer> afterTracks = new ArrayList<>();
        ArrayList<Integer> beforeTracks = new ArrayList<>();
        int thisTrack = tracks.get(0);
     
        for(int i = 1;i < tracks.size();i ++){
            if (tracks.get(i) < thisTrack)
                beforeTracks.add(tracks.get(i));
            else
                afterTracks.add(tracks.get(i));
        }
        beforeTracks.sort(Comparator.reverseOrder());
        afterTracks.sort(Comparator.naturalOrder());
        trackPath.add(thisTrack);

        while(!(beforeTracks.isEmpty() && afterTracks.isEmpty())){
            if(beforeTracks.isEmpty()){
                trackPath.addAll(afterTracks);
                afterTracks.clear();
            } else if(afterTracks.isEmpty()){
                trackPath.addAll(beforeTracks);
                beforeTracks.clear();
            } else {
                if(Math.abs(beforeTracks.get(0)-thisTrack) >= Math.abs(afterTracks.get(0)-thisTrack) ){
                    trackPath.add(thisTrack = afterTracks.remove(0));

                }else if(Math.abs(beforeTracks.get(0)-thisTrack) < Math.abs(afterTracks.get(0)-thisTrack)){
                    trackPath.add(thisTrack = beforeTracks.remove(0));
                }
            }
        }

        for (int i = 1; i < trackPath.size(); i++) {
            tracksNum += Math.abs(trackPath.get(i) - trackPath.get(i-1));
        }

    }
    
    public static void SCAN(int flag){
        tracksNum = 0;
        trackPath = new ArrayList<>();
        ArrayList<Integer> beforeTracks = new ArrayList<>();
        ArrayList<Integer> afterTracks = new ArrayList<>();

        for(int i = 1;i < tracks.size();i ++){
            if(tracks.get(i) < tracks.get(0))
                beforeTracks.add(tracks.get(i));
            else
                afterTracks.add(tracks.get(i));
        }
        beforeTracks.sort(Comparator.reverseOrder());
        afterTracks.sort(Comparator.naturalOrder());
        trackPath.add(tracks.get(0));
        if(flag == 0){
            trackPath.addAll(beforeTracks);
            trackPath.addAll(afterTracks);
        }else if (flag == 1){
            trackPath.addAll(afterTracks);
            trackPath.addAll(beforeTracks);
        }

        for (int i = 1; i < trackPath.size(); i++) {
            tracksNum += Math.abs(trackPath.get(i) - trackPath.get(i-1));
        }

    }

    public static String MessageToString(){
        return getString(trackPath);
    }

    public static String TracksToString() {
        ArrayList<Integer> track = new ArrayList<>();
        track.addAll(tracks);
        track.remove(0);
        return getString(track);
    }

    public static int getTracksNum(){
        return tracksNum;
    }

    private static String getString(ArrayList<Integer> trackPath) {
        StringBuilder trackString = new StringBuilder();
        for (int i = 0; i < trackPath.size(); i++) {
            trackString.append(trackPath.get(i));
            if(i != trackPath.size()-1)
                trackString.append(" -> ");
        }
        return trackString.toString();
    }


}