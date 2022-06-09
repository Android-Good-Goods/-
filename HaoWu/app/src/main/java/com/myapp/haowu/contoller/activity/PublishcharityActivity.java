package com.myapp.haowu.contoller.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hyphenate.util.FileUtils;
import com.myapp.haowu.R;
import com.myapp.haowu.model.Model;
import com.myapp.haowu.model.db.User;
import com.myapp.haowu.utils.HttpUtils;
import com.myapp.haowu.utils.ImageDeal;
import com.myapp.haowu.utils.RealPathFromUriUtils;
import com.myapp.haowu.utils.UserUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//发布公益活动的Activity
public class PublishcharityActivity extends AppCompatActivity {

    //定义变量
    private ImageView iv_pubcharity_back,iv_pubcharity_publish,iv_pubcharity_image;

    private TextView tv_pubcharity_title,et_pubcharity_name,et_pubcharity_detail,et_pubcharity_need,tv_pubcharity_num,
            tv_pubcharity_style,tv_pubcharity_address;

    private RelativeLayout rl_pubcharity_neednum,rl_pubcharity_style,rl_pubcharity_address;

    private Spinner sp_pubcharity_date;

    private List<String> date_list;
    private ArrayAdapter<String> date_adapter;

    //设置进度条
    private ProgressDialog pd;

    //标题和要发布还是要编辑
    private String type;

    //设置弹出的alertdialog的初值
    private CharSequence[] styleChoice = {"线上","线下"};
    private CharSequence[] pictureChoice = {"拍照","从相册选择"};

    //定义图片操作的变量
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int SELECT_PIC = 0;
    private Uri imageUri; //图片路径
    private Uri cropImageUri; //裁剪后的图片路径
    private String filename; //图片名称

    private Bitmap charityImage = null;

    Map<String, Object> charitydata = new HashMap<>();

    //定义监听变量
    private PublishcharityActivity.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishcharity);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        charitydata = (Map<String, Object>) intent.getSerializableExtra("data");

        //初始化视图
        initView();
        //初始化数据
        initData();
    }

    private void initView() {
        //初始化进度条
        pd = new ProgressDialog(PublishcharityActivity.this);
        pd.setMessage("发布中...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);

        //日期设置
        date_list = new ArrayList<String>();
        date_list.add("请选择...");
        date_list.add("一天");
        date_list.add("三天");
        date_list.add("七天");
        date_list.add("十五天");
        date_list.add("三十天");
        //ImageView
        iv_pubcharity_back = (ImageView)findViewById(R.id.iv_pubcharity_back);
        iv_pubcharity_publish = (ImageView)findViewById(R.id.iv_pubcharity_publish);
        iv_pubcharity_image = (ImageView)findViewById(R.id.iv_pubcharity_image);
        //TextView
        tv_pubcharity_title = (TextView)findViewById(R.id.tv_pubcharity_title);
        et_pubcharity_name = (TextView)findViewById(R.id.et_pubcharity_name);
        et_pubcharity_detail = (TextView)findViewById(R.id.et_pubcharity_detail);
        et_pubcharity_need = (TextView)findViewById(R.id.et_pubcharity_need);
        tv_pubcharity_num = (TextView)findViewById(R.id.tv_pubcharity_num);
        tv_pubcharity_style = (TextView)findViewById(R.id.tv_pubcharity_style);
        tv_pubcharity_address = (TextView)findViewById(R.id.tv_pubcharity_address);
        //RelativeLayout
        rl_pubcharity_neednum = (RelativeLayout)findViewById(R.id.rl_pubcharity_neednum);
        rl_pubcharity_style = (RelativeLayout)findViewById(R.id.rl_pubcharity_style);
        rl_pubcharity_address = (RelativeLayout)findViewById(R.id.rl_pubcharity_address);
        //Spinner
        sp_pubcharity_date = (Spinner)findViewById(R.id.sp_pubcharity_date);
        //设置适配器
        date_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, date_list);
        date_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_pubcharity_date.setAdapter(date_adapter);

        //设置监听
        listener = new OnClickListener();
        iv_pubcharity_back.setOnClickListener(listener);
        iv_pubcharity_publish.setOnClickListener(listener);
        iv_pubcharity_image.setOnClickListener(listener);
        rl_pubcharity_neednum.setOnClickListener(listener);
        rl_pubcharity_style.setOnClickListener(listener);
        rl_pubcharity_address.setOnClickListener(listener);
    }

    //初始化数据
    private void initData(){
        if("editcharity".equals(type)){
            tv_pubcharity_title.setText("编辑公益");
            //编辑公益活动是不可更改图片
            iv_pubcharity_image.setClickable(false);
            filename = charitydata.get("cimage").toString();
            //加载图片
            RequestOptions options = new RequestOptions();
            options.fitCenter()
                    .placeholder(R.drawable.ic_moren_goods)
                    .error(R.drawable.ic_moren_goods)
                    .fallback(R.drawable.ic_moren_goods);
            Glide.with(this)
                    .applyDefaultRequestOptions(options)
                    .load(charitydata.get("cimage").toString())
                    .into(iv_pubcharity_image);
            //设置活动标题
            et_pubcharity_name.setText(charitydata.get("cname").toString());
            //设置细节
            et_pubcharity_detail.setText(charitydata.get("cdetail").toString());
            //设置公益需求
            et_pubcharity_need.setText(charitydata.get("cneed").toString());
            //设置人数
            tv_pubcharity_num.setText(charitydata.get("cnumber").toString());
            //设置活动方式
            tv_pubcharity_style.setText(charitydata.get("ctype").toString());
            //设置发货地点
            tv_pubcharity_address.setText(charitydata.get("caddress").toString());
        }else {
            //设置标题
            tv_pubcharity_title.setText("发起公益");
            //设置发货地点
            tv_pubcharity_address.setText(MainActivity.myAddress);
        }
    }

    //监听
    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.iv_pubcharity_back:
                    finish();
                    break;
                case R.id.iv_pubcharity_publish:
                    publish();
                    break;
                case R.id.iv_pubcharity_image:
                    addImage();
                    break;
                case R.id.rl_pubcharity_neednum:
                    needNum();
                    break;
                case R.id.rl_pubcharity_style:
                    activityStyle();
                    break;
                case R.id.rl_pubcharity_address:
                    Intent addressIntent = new Intent(PublishcharityActivity.this, PubaddressActivity.class);
                    startActivityForResult(addressIntent, 3);
                    break;
            }
        }
    }

    //handler处理
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    int charityBack;
                    if(!msg.obj.toString().trim().isEmpty()&&!msg.obj.toString().trim().equals("-1"))
                    {
                        try{
                            JSONObject charityResult = new JSONObject(msg.obj.toString().trim());
                            charityBack = charityResult.getInt("code");
                            if(charityBack == 1){
                                pd.cancel();
                                Toast.makeText(PublishcharityActivity.this,"公益发布成功",Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                pd.cancel();
                                Toast.makeText(PublishcharityActivity.this,"公益发布失败",Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            pd.cancel();
                            e.printStackTrace();
                        }
                    }else {
                        pd.cancel();
                        Toast.makeText(PublishcharityActivity.this,"请检查网络",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    int republishBack;
                    if(!msg.obj.toString().trim().isEmpty()&&!msg.obj.toString().trim().equals("-1"))
                    {
                        try{
                            JSONObject republishResult = new JSONObject(msg.obj.toString().trim());
                            republishBack = republishResult.getInt("code");
                            if(republishBack == 1){
                                pd.cancel();
                                Toast.makeText(PublishcharityActivity.this,"编辑成功",Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                pd.cancel();
                                Toast.makeText(PublishcharityActivity.this,"编辑失败",Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            pd.cancel();
                            e.printStackTrace();
                        }
                    }else {
                        pd.cancel();
                        Toast.makeText(PublishcharityActivity.this,"请检查网络",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case SELECT_PIC://相册
                String path = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
                File file = new File(path);
                filename = file.getName();
                if (Build.VERSION.SDK_INT >= 24) {
                    try {
                        imageUri = FileUtils.getUriForFile(PublishcharityActivity.this, file);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                } else {
                    imageUri = Uri.fromFile(file);
                }
                //裁剪图片，返回裁剪好的Uri
                cropImageUri = ImageDeal.startUCrop(PublishcharityActivity.this, imageUri, CROP_PHOTO, 1200, 600);
                break;
            case TAKE_PHOTO://相机
                try {
                    //广播刷新相册
                    Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intentBc.setData(imageUri);
                    this.sendBroadcast(intentBc);
                    //裁剪图片，返回裁剪好的Uri
                    cropImageUri = ImageDeal.startUCrop(PublishcharityActivity.this, imageUri, CROP_PHOTO, 1200, 600);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case CROP_PHOTO:
                //图片解析成Bitmap对象
                try {
                    charityImage = BitmapFactory.decodeStream(getContentResolver().openInputStream(cropImageUri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //加载商品图片
                Glide.with(PublishcharityActivity.this)
                        .load(cropImageUri)
                        .placeholder(R.drawable.ic_moren_goods)
                        .error(R.drawable.ic_moren_goods)
                        .into(iv_pubcharity_image);
                break;
            case 3:
                tv_pubcharity_address.setText(data.getStringExtra("address").trim());
                break;
        }
    }

    //添加商品图片
    private void addImage(){
        //设置日期的格式
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        new AlertDialog.Builder(PublishcharityActivity.this)
                .setTitle("添加图片")
                .setItems(pictureChoice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try{
                            switch (which)
                            {
                                case 0://拍照
                                    //图片名称 时间命名
                                    Date date = new Date(System.currentTimeMillis());
                                    filename = format.format(date);
                                    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                                    File outputImage = new File(path,filename+".jpg");
                                    filename = filename + ".jpg";
                                    System.out.println(outputImage);
                                    try {
                                        if(outputImage.exists()) {
                                            outputImage.delete();
                                        }
                                        outputImage.createNewFile();
                                    } catch(IOException e) {
                                        e.printStackTrace();
                                    }
                                    //将File对象转换为Uri并启动照相程序
                                    if (Build.VERSION.SDK_INT >= 24) {
                                        try{
                                            imageUri = FileUtils.getUriForFile(PublishcharityActivity.this, outputImage);
                                        }catch (NullPointerException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        imageUri = Uri.fromFile(outputImage);
                                    }
                                    Intent tTntent = new Intent("android.media.action.IMAGE_CAPTURE"); //照相
                                    tTntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
                                    startActivityForResult(tTntent,TAKE_PHOTO); //启动照相
                                    break;
                                case 1://从相册选择
                                    Intent intent = new Intent(Intent.ACTION_PICK);
                                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                                    startActivityForResult(intent,SELECT_PIC);
                                    break;
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                })
                .create()
                .show();
    }

    //上传公益信息
    private void publish() {
        Map<String, String> params = new HashMap<>();
        User user = UserUtils.getCurrentUser();
        //获取时间
        final Date d = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取图片
        if(filename == null) {
            Toast.makeText(PublishcharityActivity.this, "请选择图片！", Toast.LENGTH_SHORT).show();
        }else if("".equals(et_pubcharity_name.getText().toString().trim())){
            Toast.makeText(PublishcharityActivity.this, "请输入公益标题！", Toast.LENGTH_SHORT).show();
        }else if("".equals(et_pubcharity_detail.getText().toString().trim())){
            Toast.makeText(PublishcharityActivity.this, "请输入公益细节！", Toast.LENGTH_SHORT).show();
        }else if("".equals(et_pubcharity_need.getText().toString().trim())){
            Toast.makeText(PublishcharityActivity.this, "请输入公益需求！", Toast.LENGTH_SHORT).show();
        }else if("请选择...".equals(sp_pubcharity_date.getSelectedItem().toString().trim())){
            Toast.makeText(PublishcharityActivity.this, "请选择公益期限！", Toast.LENGTH_SHORT).show();
        }else if("null".equals(UserUtils.getCurrentUser().getAddress().trim())){
            Toast.makeText(PublishcharityActivity.this, "请先设置地址！", Toast.LENGTH_SHORT).show();
        }else{
            if("publishcharity".equals(type)){
                //上传数据
                AlertDialog.Builder normalDialog = new AlertDialog.Builder(PublishcharityActivity.this);
                normalDialog.setTitle("提示");
                normalDialog.setMessage("图片发布后不可更改，确定发布吗？");
                normalDialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pd.show();
                        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                            @Override
                            public void run() {
                                //设置数据
                                params.put("requesttop","publishcharity");
                                params.put("cname", et_pubcharity_name.getText().toString().trim());
                                params.put("imagestr", ImageDeal.Bitmap2String(charityImage));
                                params.put("imagename",filename);
                                params.put("cdetail", et_pubcharity_detail.getText().toString().trim());
                                params.put("cneed", et_pubcharity_need.getText().toString().trim());
                                params.put("cnumber", tv_pubcharity_num.getText().toString().trim());
                                if("一天".equals(sp_pubcharity_date.getSelectedItem().toString().trim())){
                                    params.put("cdate", transDate(1));
                                }else if("三天".equals(sp_pubcharity_date.getSelectedItem().toString().trim())){
                                    params.put("cdate", transDate(3));
                                }else if("七天".equals(sp_pubcharity_date.getSelectedItem().toString().trim())){
                                    params.put("cdate", transDate(7));
                                }else if("十五天".equals(sp_pubcharity_date.getSelectedItem().toString().trim())){
                                    params.put("cdate", transDate(15));
                                }else if("三十天".equals(sp_pubcharity_date.getSelectedItem().toString().trim())){
                                    params.put("cdate", transDate(30));
                                }else params.put("cdate", transDate(0));
                                params.put("cstyle", tv_pubcharity_style.getText().toString().trim());
                                params.put("uaccount", user.getAccount());
                                params.put("caddress", tv_pubcharity_address.getText().toString().trim());
                                params.put("cscannum", "0");
                                params.put("cjoinnum", "0");
                                params.put("cstate", "1");
                                params.put("ctime", sdf.format(d));
                                String strUrlpath = getResources().getString(R.string.burl) + "Publish_Servlet";
                                String Result = HttpUtils.submitPostData(strUrlpath, params, "utf-8");
                                System.out.println("结果为：" + Result);
                                Message message = new Message();
                                message.what = 1;
                                message.obj = Result;
                                handler.sendMessage(message);
                            }
                        });
                    }
                });
                normalDialog.setNegativeButton("关闭",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //...To-do
                            }
                        });
                // 显示
                normalDialog.show();
            }else {
                //上传修改后的数据
                AlertDialog.Builder normalDialog = new AlertDialog.Builder(PublishcharityActivity.this);
                normalDialog.setTitle("提示");
                normalDialog.setMessage("确定更改吗？");
                normalDialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pd.show();
                        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                            @Override
                            public void run() {
                                //设置数据
                                params.put("requesttop","republishcharity");
                                params.put("cid", charitydata.get("cid").toString());
                                params.put("cname", et_pubcharity_name.getText().toString().trim());
                                params.put("cdetail", et_pubcharity_detail.getText().toString().trim());
                                params.put("cneed", et_pubcharity_need.getText().toString().trim());
                                params.put("cnumber", tv_pubcharity_num.getText().toString().trim());
                                if("一天".equals(sp_pubcharity_date.getSelectedItem().toString().trim())){
                                    params.put("cdate", transDate(1));
                                }else if("三天".equals(sp_pubcharity_date.getSelectedItem().toString().trim())){
                                    params.put("cdate", transDate(3));
                                }else if("七天".equals(sp_pubcharity_date.getSelectedItem().toString().trim())){
                                    params.put("cdate", transDate(7));
                                }else if("十五天".equals(sp_pubcharity_date.getSelectedItem().toString().trim())){
                                    params.put("cdate", transDate(15));
                                }else if("三十天".equals(sp_pubcharity_date.getSelectedItem().toString().trim())){
                                    params.put("cdate", transDate(30));
                                }else params.put("cdate", transDate(0));
                                params.put("cstyle", tv_pubcharity_style.getText().toString().trim());
                                params.put("caddress", tv_pubcharity_address.getText().toString().trim());
                                //发起请求
                                String strUrlpath = getResources().getString(R.string.burl) + "Publish_Servlet";
                                String Result = HttpUtils.submitPostData(strUrlpath, params, "utf-8");
                                System.out.println("结果为：" + Result);
                                Message message = new Message();
                                message.what = 2;
                                message.obj = Result;
                                handler.sendMessage(message);
                            }
                        });
                    }
                });
                normalDialog.setNegativeButton("关闭",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //...To-do
                            }
                        });
                // 显示
                normalDialog.show();
            }
        }
    }

    //所需人数
    private void needNum(){
        //加载布局
        final LinearLayout layout_number = (LinearLayout)getLayoutInflater().inflate(R.layout.layout_number,null);
        new AlertDialog.Builder(this)
                .setView(layout_number)
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//设置确定按钮
                    @SuppressLint("ResourceAsColor")
                    @Override//处理确定按钮点击事件
                    public void onClick(DialogInterface dialog, int which) {
                        EditText neednum = layout_number.findViewById(R.id.et_layout_number);
                        if(neednum.getText().toString().trim().isEmpty()){
                            Toast.makeText(PublishcharityActivity.this,"输入不能为空！",Toast.LENGTH_SHORT).show();
                        } else if(Double.valueOf(neednum.getText().toString().trim()) <= 0){
                            Toast.makeText(PublishcharityActivity.this,"输入有误！",Toast.LENGTH_SHORT).show();
                        }else {
                            //将人数显示出来
                            tv_pubcharity_num.setText(neednum.getText().toString());
                        }
                    }
                })
                .setNegativeButton("取消",new DialogInterface.OnClickListener() {//设置确定按钮
                    @Override//处理取消按钮点击事件
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();
    }

    //活动期限转换
    private String transDate(int data){
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, data);
        return sdf.format(c.getTime().getTime());
    }

    //活动方式设置
    private void activityStyle(){
        new AlertDialog.Builder(PublishcharityActivity.this)
                .setItems(styleChoice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            switch (which) {
                                case 0://线上活动
                                    tv_pubcharity_style.setText("线上");
                                    break;
                                case 1://线下活动
                                    tv_pubcharity_style.setText("线下");
                                    break;
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }).create().show();
    }
}
