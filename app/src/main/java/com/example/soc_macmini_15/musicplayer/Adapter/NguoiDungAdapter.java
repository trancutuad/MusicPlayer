package com.example.soc_macmini_15.musicplayer.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soc_macmini_15.musicplayer.Model.NguoiDung;
import com.example.soc_macmini_15.musicplayer.R;
import com.example.soc_macmini_15.musicplayer.dao.NguoiDungDao;

import java.util.List;


public class NguoiDungAdapter extends BaseAdapter {
    List<NguoiDung> arrNguoidung;
    public Activity context;
    public LayoutInflater inflater;
    NguoiDungDao nguoiDungDao;

    public NguoiDungAdapter(List<NguoiDung> arrNguoidung, Activity context) {
        this.arrNguoidung = arrNguoidung;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDao = new NguoiDungDao(context);
    }

    @Override
    public int getCount() {
        return arrNguoidung.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoidung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView imgavataruser;
        TextView txtUser;
        TextView txtPass;
        ImageView imgDelete,iconchangepassword;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder ;
        if (convertView == null) {
            holder= new ViewHolder();
            convertView = inflater.inflate(R.layout.customuser, null);
            holder.imgavataruser = (ImageView) convertView.findViewById(R.id.imgavataruser);
            holder.txtUser = (TextView) convertView.findViewById(R.id.tvUser);
            holder.txtPass = (TextView) convertView.findViewById(R.id.tvPass);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgdeleteuser);

            //xoa
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    nguoiDungDao.deleteNguoiDungByID(arrNguoidung.get(position).getUserName());
                    arrNguoidung.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        NguoiDung _entry = (NguoiDung) arrNguoidung.get(position);

        holder.txtUser.setText(_entry.getUserName());
        holder.txtPass.setText(_entry.getPassword());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<NguoiDung> items){
        this.arrNguoidung=items;
        notifyDataSetChanged();
    }
}
